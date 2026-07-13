package kr.co.springkmarketapp.service.order;

import kr.co.springkmarketapp.dao.coupon.CouponDAO;
import kr.co.springkmarketapp.dao.order.CartDAO;
import kr.co.springkmarketapp.dao.order.OrdersDAO;
import kr.co.springkmarketapp.dto.coupon.OrderCouponDTO;
import kr.co.springkmarketapp.dto.my.MemberPointDTO;
import kr.co.springkmarketapp.dto.order.*;
import kr.co.springkmarketapp.service.notification.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrdersService {

    private final CouponDAO couponDAO;
    private final CartDAO cartDAO;
    private final OrdersDAO ordersDAO;
    private final NotificationService notificationService;

    public OrderDiscountDTO getOrderDiscountInfo(Integer memberNo) {

        Integer availablePoint = ordersDAO.selectAvailablePoint(memberNo);

        List<OrderCouponDTO> coupons =
                couponDAO.selectAvailableCoupons(memberNo);

        return OrderDiscountDTO.builder()
                .availablePoint(availablePoint != null ? availablePoint : 0)
                .coupons(coupons)
                .build();
    }

    private String normalizePaymentMethod(String paymentMethod) {
        if (paymentMethod == null || paymentMethod.isBlank()) {
            throw new IllegalArgumentException("결제수단을 선택하세요.");
        }

        return switch (paymentMethod.trim()) {
            case "credit", "신용카드" -> "신용카드";
            case "check", "체크카드" -> "체크카드";
            case "bank", "계좌이체" -> "계좌이체";
            case "deposit", "무통장입금" -> "무통장입금";
            case "phone", "휴대폰결제" -> "휴대폰결제";
            case "kakao", "카카오페이" -> "카카오페이";
            default -> throw new IllegalArgumentException("지원하지 않는 결제수단입니다.");
        };
    }

    @Transactional
    public Long createOrder(
            Integer memberNo,
            String memberGrade,
            OrderCreateRequestDTO request
    ) {
        validateOrderRequest(request);

        List<Integer> cartNos = request.getCartNos()
                .stream()
                .filter(Objects::nonNull)
                .distinct()
                .toList();

        if (cartNos.isEmpty()) {
            throw new IllegalArgumentException("주문 상품이 없습니다.");
        }

        List<CartListDTO> orderItems =
                cartDAO.selectCartListByMemberNoAndCartNos(memberNo, cartNos);

        if (orderItems.size() != cartNos.size()) {
            throw new IllegalArgumentException("주문할 수 없는 장바구니 상품이 포함되어 있습니다.");
        }

        int availablePoint = n(ordersDAO.selectAvailablePoint(memberNo));
        int usedPoint = request.getUsedPoint() != null ? request.getUsedPoint() : 0;

        if (usedPoint < 0) {
            throw new IllegalArgumentException("사용 포인트가 올바르지 않습니다.");
        }

        if (usedPoint > availablePoint) {
            throw new IllegalArgumentException("보유 포인트를 초과할 수 없습니다.");
        }

        int totalPrice = calculateTotalPrice(orderItems);
        int discountPrice = calculateProductDiscount(orderItems);
        int deliveryFee = calculateDeliveryFee(orderItems);

        int productSaleTotal = totalPrice - discountPrice;

        CouponCalcResult couponCalcResult =
                calculateCouponDiscount(memberNo, request.getCouponIssueNo(), orderItems);

        int couponDiscount = couponCalcResult.getCouponDiscount();

        int payableBeforePoint =
                productSaleTotal + deliveryFee - couponDiscount;

        if (usedPoint > payableBeforePoint) {
            throw new IllegalArgumentException("결제금액보다 많은 포인트를 사용할 수 없습니다.");
        }

        int payPrice = Math.max(
                payableBeforePoint - usedPoint,
                0
        );

        int pointSave = calculatePointSave(
                orderItems,
                memberGrade,
                productSaleTotal,
                couponDiscount,
                usedPoint
        );

        OrderSaveDTO orderSaveDTO = OrderSaveDTO.builder()
                .orderCode(createOrderCode())
                .memberNo(memberNo)
                .ordererName(request.getReceiverName())
                .ordererHp(request.getReceiverHp())
                .totalPrice(totalPrice)
                .discountPrice(discountPrice)
                .couponDiscount(couponDiscount)
                .deliveryFee(deliveryFee)
                .pointUse(usedPoint)
                .pointSave(pointSave)
                .payPrice(payPrice)
                .paymentMethod(normalizePaymentMethod(request.getPaymentMethod()))
                .paymentStatus("결제완료")
                .orderStatus("주문완료")
                .build();

        ordersDAO.insertOrder(orderSaveDTO);

        Long orderNo = orderSaveDTO.getOrderNo();

        if (orderNo == null) {
            throw new IllegalArgumentException("주문번호 생성에 실패했습니다.");
        }

        Map<Integer, Integer> sellerDeliveryMap =
                insertDeliveries(orderNo, request, orderItems);

        insertOrderItems(
                orderNo,
                orderItems,
                sellerDeliveryMap,
                couponCalcResult.getItemCouponDiscountMap()
        );

        decreaseStock(orderItems);

        if (request.getCouponIssueNo() != null) {
            int couponUseResult = couponDAO.updateCouponUsed(
                    memberNo,
                    request.getCouponIssueNo(),
                    orderNo
            );

            if (couponUseResult != 1) {
                throw new IllegalArgumentException("이미 사용했거나 사용할 수 없는 쿠폰입니다.");
            }
        }

        // 포인트 차감/적립 + member_point 이력 저장
        processPoint(memberNo, orderNo, usedPoint, pointSave);

        cartDAO.deleteCartOptionsByMemberNo(memberNo, cartNos);
        cartDAO.deleteCartsByMemberNo(memberNo, cartNos);

        notificationService.createOrderComplete(orderNo);

        return orderNo;
    }

    private void validateOrderRequest(OrderCreateRequestDTO request) {
        if (request.getCartNos() == null || request.getCartNos().isEmpty()) {
            throw new IllegalArgumentException("주문 상품이 없습니다.");
        }

        if (request.getReceiverName() == null || request.getReceiverName().isBlank()) {
            throw new IllegalArgumentException("수령자를 입력하세요.");
        }

        if (request.getReceiverHp() == null || request.getReceiverHp().isBlank()) {
            throw new IllegalArgumentException("휴대폰 번호를 입력하세요.");
        }

        if (request.getZip() == null || request.getZip().isBlank()) {
            throw new IllegalArgumentException("우편번호를 입력하세요.");
        }

        if (request.getAddr1() == null || request.getAddr1().isBlank()) {
            throw new IllegalArgumentException("주소를 입력하세요.");
        }

        if (request.getPaymentMethod() == null || request.getPaymentMethod().isBlank()) {
            throw new IllegalArgumentException("결제수단을 선택하세요.");
        }
    }

    private int calculateTotalPrice(List<CartListDTO> items) {
        return items.stream()
                .mapToInt(item -> n(item.getPrice()) * n(item.getQuantity()))
                .sum();
    }

    private int calculateProductDiscount(List<CartListDTO> items) {
        return items.stream()
                .mapToInt(item -> {
                    int discount = n(item.getPrice()) * n(item.getDiscountRate()) / 100;
                    return discount * n(item.getQuantity());
                })
                .sum();
    }

    private int calculateDeliveryFee(List<CartListDTO> items) {
        return items.stream()
                .mapToInt(item -> n(item.getDeliveryFee()))
                .sum();
    }

    private int getSalePrice(CartListDTO item) {
        int price = n(item.getPrice());
        int discountRate = n(item.getDiscountRate());

        int discount = price * discountRate / 100;

        return price - discount;
    }

    private int getItemSubtotal(CartListDTO item) {
        return getSalePrice(item) * n(item.getQuantity());
    }

    private CouponCalcResult calculateCouponDiscount(
            Integer memberNo,
            Long couponIssueNo,
            List<CartListDTO> orderItems
    ) {
        if (couponIssueNo == null) {
            return CouponCalcResult.empty();
        }

        OrderCouponDTO coupon =
                couponDAO.selectAvailableCouponForOrder(memberNo, couponIssueNo);

        if (coupon == null) {
            throw new IllegalArgumentException("사용할 수 없는 쿠폰입니다.");
        }

        List<CartListDTO> eligibleItems =
                getEligibleItems(coupon, orderItems);

        if (eligibleItems.isEmpty()) {
            throw new IllegalArgumentException("이 주문에 적용할 수 없는 쿠폰입니다.");
        }

        int eligibleProductTotal = eligibleItems.stream()
                .mapToInt(this::getItemSubtotal)
                .sum();

        if (n(coupon.getMinOrderPrice()) > 0
                && eligibleProductTotal < n(coupon.getMinOrderPrice())) {
            throw new IllegalArgumentException("쿠폰 최소 주문금액을 만족하지 않습니다.");
        }

        Map<Integer, Integer> itemCouponDiscountMap = new HashMap<>();

        int couponDiscount = 0;

        if ("FREE_SHIPPING".equals(coupon.getCouponType())
                || "FREE_SHIPPING".equals(coupon.getBenefitType())) {

            couponDiscount = eligibleItems.stream()
                    .mapToInt(item -> n(item.getDeliveryFee()))
                    .sum();
        }

        else if ("AMOUNT".equals(coupon.getBenefitType())) {
            couponDiscount = Math.min(
                    n(coupon.getBenefitValue()),
                    eligibleProductTotal
            );

            allocateAmountDiscount(
                    eligibleItems,
                    couponDiscount,
                    itemCouponDiscountMap
            );
        }

        else if ("RATE".equals(coupon.getBenefitType())) {

            if ("ORDER".equals(coupon.getCouponType())) {
                int rate = n(coupon.getBenefitValue());

                couponDiscount =
                        eligibleProductTotal * rate / 100;

                allocateRateDiscount(
                        eligibleItems,
                        rate,
                        itemCouponDiscountMap
                );
            }

            else if ("PRODUCT".equals(coupon.getCouponType())) {
                CartListDTO targetItem = eligibleItems.stream()
                        .max(Comparator.comparingInt(item ->
                                getItemSubtotal(item) * n(coupon.getBenefitValue()) / 100
                        ))
                        .orElseThrow();

                couponDiscount =
                        getItemSubtotal(targetItem) * n(coupon.getBenefitValue()) / 100;

                itemCouponDiscountMap.put(
                        targetItem.getCartNo(),
                        couponDiscount
                );
            }
        }

        if (n(coupon.getMaxDiscountPrice()) > 0
                && couponDiscount > n(coupon.getMaxDiscountPrice())) {

            couponDiscount = n(coupon.getMaxDiscountPrice());

            itemCouponDiscountMap.clear();

            allocateAmountDiscount(
                    eligibleItems,
                    couponDiscount,
                    itemCouponDiscountMap
            );
        }

        return new CouponCalcResult(
                couponDiscount,
                itemCouponDiscountMap
        );
    }

    private List<CartListDTO> getEligibleItems(
            OrderCouponDTO coupon,
            List<CartListDTO> orderItems
    ) {
        String couponType = coupon.getCouponType();

        // 주문 전체 쿠폰은 seller_no를 보지 않는다.
        // 회원가입 축하쿠폰도 ORDER로 변환되므로 전체 주문상품에 적용되어야 한다.
        if ("ORDER".equals(couponType)) {
            return orderItems;
        }

        // 개별상품 할인은 seller_no가 있어야 해당 판매자 상품에만 적용한다.
        if ("PRODUCT".equals(couponType)) {
            if (coupon.getSellerNo() == null) {
                return List.of();
            }

            return orderItems.stream()
                    .filter(item -> Objects.equals(
                            item.getSellerNo(),
                            coupon.getSellerNo()
                    ))
                    .toList();
        }

        // 배송비 무료는 seller_no가 있으면 해당 판매자 배송비만,
        // seller_no가 없으면 전체 배송비에 적용한다.
        if ("FREE_SHIPPING".equals(couponType)) {
            if (coupon.getSellerNo() == null) {
                return orderItems;
            }

            return orderItems.stream()
                    .filter(item -> Objects.equals(
                            item.getSellerNo(),
                            coupon.getSellerNo()
                    ))
                    .toList();
        }

        return List.of();
    }

    private void allocateAmountDiscount(
            List<CartListDTO> eligibleItems,
            int couponDiscount,
            Map<Integer, Integer> itemCouponDiscountMap
    ) {
        int remaining = couponDiscount;

        List<CartListDTO> sortedItems = eligibleItems.stream()
                .sorted(Comparator.comparingInt(this::getItemSubtotal).reversed())
                .toList();

        for (CartListDTO item : sortedItems) {
            if (remaining <= 0) {
                break;
            }

            int itemDiscount = Math.min(
                    remaining,
                    getItemSubtotal(item)
            );

            itemCouponDiscountMap.put(
                    item.getCartNo(),
                    itemDiscount
            );

            remaining -= itemDiscount;
        }
    }

    private void allocateRateDiscount(
            List<CartListDTO> eligibleItems,
            int rate,
            Map<Integer, Integer> itemCouponDiscountMap
    ) {
        for (CartListDTO item : eligibleItems) {
            int discount = getItemSubtotal(item) * rate / 100;

            if (discount > 0) {
                itemCouponDiscountMap.put(
                        item.getCartNo(),
                        discount
                );
            }
        }
    }

    private int calculatePointSave(
            List<CartListDTO> orderItems,
            String memberGrade,
            int productSaleTotal,
            int couponDiscount,
            int usedPoint
    ) {
        int productBasePoint = orderItems.stream()
                .mapToInt(item -> n(item.getPoint()) * n(item.getQuantity()))
                .sum();

        int pointBasePrice = Math.max(
                productSaleTotal - couponDiscount - usedPoint,
                0
        );

        int gradePoint = pointBasePrice * getGradePointRate(memberGrade) / 100;

        return productBasePoint + gradePoint;
    }

    private int getGradePointRate(String grade) {
        if (grade == null) {
            return 1;
        }

        return switch (grade.toUpperCase()) {
            case "VVIP", "5" -> 5;
            case "VIP", "4" -> 4;
            case "GOLD", "3" -> 3;
            case "SILVER", "2" -> 2;
            default -> 1;
        };
    }

    private Map<Integer, Integer> insertDeliveries(
            Long orderNo,
            OrderCreateRequestDTO request,
            List<CartListDTO> orderItems
    ) {
        Map<Integer, List<CartListDTO>> sellerItemsMap = new LinkedHashMap<>();

        for (CartListDTO item : orderItems) {
            Integer sellerNo = item.getSellerNo();

            sellerItemsMap
                    .computeIfAbsent(sellerNo, key -> new java.util.ArrayList<>())
                    .add(item);
        }

        Map<Integer, Integer> sellerDeliveryMap = new HashMap<>();

        for (Map.Entry<Integer, List<CartListDTO>> entry : sellerItemsMap.entrySet()) {
            Integer sellerNo = entry.getKey();

            int sellerDeliveryFee = entry.getValue()
                    .stream()
                    .mapToInt(item -> n(item.getDeliveryFee()))
                    .sum();

            DeliveryDTO deliveryDTO = DeliveryDTO.builder()
                    .orderNo(orderNo)
                    .sellerNo(sellerNo)
                    .receiverName(request.getReceiverName())
                    .receiverHp(request.getReceiverHp())
                    .zip(request.getZip())
                    .addr1(request.getAddr1())
                    .addr2(request.getAddr2())
                    .courier(null)
                    .invoiceNo(null)
                    .deliveryFee(sellerDeliveryFee)
                    .deliveryStatus("배송준비")
                    .memo(request.getDeliveryRequest())
                    .build();

            ordersDAO.insertDelivery(deliveryDTO);

            sellerDeliveryMap.put(
                    sellerNo,
                    deliveryDTO.getDeliveryNo()
            );
        }

        return sellerDeliveryMap;
    }

    private void insertOrderItems(
            Long orderNo,
            List<CartListDTO> orderItems,
            Map<Integer, Integer> sellerDeliveryMap,
            Map<Integer, Integer> itemCouponDiscountMap
    ) {
        for (CartListDTO item : orderItems) {
            int itemCouponDiscount =
                    itemCouponDiscountMap.getOrDefault(item.getCartNo(), 0);

            int itemTotalPrice = Math.max(
                    getItemSubtotal(item) - itemCouponDiscount,
                    0
            );

            OrderItemDTO orderItemDTO =
                    OrderItemDTO.builder()
                            .orderNo(orderNo)
                            .deliveryNo(sellerDeliveryMap.get(item.getSellerNo()))
                            .productNo(item.getProductNo())
                            .sellerNo(item.getSellerNo())
                            .productName(item.getProductName())
                            .productImage(item.getImagePath())
                            .price(n(item.getPrice()))
                            .discountRate(n(item.getDiscountRate()))
                            .couponDiscount(itemCouponDiscount)
                            .point(n(item.getPoint()) * n(item.getQuantity()))
                            .quantity(n(item.getQuantity()))
                            .totalPrice(itemTotalPrice)
                            .itemStatus("주문완료")
                            .build();

            // 1. order_item 저장
            ordersDAO.insertOrderItem(orderItemDTO);

            Long orderItemNo = orderItemDTO.getOrderItemNo();

            if (orderItemNo == null) {
                throw new IllegalArgumentException("주문상품 번호 생성에 실패했습니다.");
            }

            // 2. cart_option + product_option 조회
            List<OrderItemOptionDTO> optionList =
                    cartDAO.selectOrderItemOptionsByCartNo(item.getCartNo());

            // 3. order_item_option 저장
            for (OrderItemOptionDTO optionDTO : optionList) {
                optionDTO.setOrderItemNo(orderItemNo);
                ordersDAO.insertOrderItemOption(optionDTO);
            }
        }
    }

    private void processPoint(
            Integer memberNo,
            Long orderNo,
            int usedPoint,
            int pointSave
    ) {
        // 1. 포인트 사용 처리
        if (usedPoint > 0) {
            int useResult = ordersDAO.updateMemberPointUse(memberNo, usedPoint);

            if (useResult != 1) {
                throw new IllegalArgumentException("포인트 차감에 실패했습니다.");
            }

            int balancePoint = n(ordersDAO.selectAvailablePoint(memberNo));

            ordersDAO.insertMemberPoint(
                    MemberPointDTO.builder()
                            .memberNo(memberNo)
                            .orderNo(orderNo)
                            .pointType("REVOKED")
                            .pointValue(usedPoint)
                            .balancePoint(balancePoint)
                            .reason("주문 결제 사용")
                            .expireDate(null)
                            .build()
            );
        }

        // 2. 포인트 적립 처리
        if (pointSave > 0) {
            int earnResult = ordersDAO.updateMemberPointEarn(memberNo, pointSave);

            if (earnResult != 1) {
                throw new IllegalArgumentException("포인트 적립에 실패했습니다.");
            }

            int balancePoint = n(ordersDAO.selectAvailablePoint(memberNo));

            ordersDAO.insertMemberPoint(
                    MemberPointDTO.builder()
                            .memberNo(memberNo)
                            .orderNo(orderNo)
                            .pointType("적립")
                            .pointValue(pointSave)
                            .balancePoint(balancePoint)
                            .reason("주문 적립")
                            .expireDate(null)
                            .build()
            );
        }
    }

    private void decreaseStock(List<CartListDTO> orderItems) {
        for (CartListDTO item : orderItems) {
            int result = ordersDAO.updateProductStock(
                    item.getProductNo(),
                    n(item.getQuantity())
            );

            if (result == 0) {
                throw new IllegalArgumentException(
                        "상품 재고가 부족합니다. 상품번호: " + item.getProductNo()
                );
            }
        }
    }

    private String createOrderCode() {
        String date = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

        String random = UUID.randomUUID()
                .toString()
                .substring(0, 8)
                .toUpperCase();

        return "KM" + date + "-" + random;
    }

    private int n(Integer value) {
        return value != null ? value : 0;
    }

    private static class CouponCalcResult {

        private final int couponDiscount;
        private final Map<Integer, Integer> itemCouponDiscountMap;

        private CouponCalcResult(
                int couponDiscount,
                Map<Integer, Integer> itemCouponDiscountMap
        ) {
            this.couponDiscount = couponDiscount;
            this.itemCouponDiscountMap = itemCouponDiscountMap;
        }

        private static CouponCalcResult empty() {
            return new CouponCalcResult(
                    0,
                    new HashMap<>()
            );
        }

        public int getCouponDiscount() {
            return couponDiscount;
        }

        public Map<Integer, Integer> getItemCouponDiscountMap() {
            return itemCouponDiscountMap;
        }
    }

    @Transactional(readOnly = true)
    public OrdersDTO selectOrderComplete(Integer memberNo, Long orderNo) {

        if (orderNo == null) {
            throw new IllegalArgumentException("주문번호가 없습니다.");
        }

        return ordersDAO.selectOrderComplete(memberNo, orderNo);
    }

    @Transactional(readOnly = true)
    public List<OrderItemDTO> selectOrderCompleteItems(Long orderNo) {

        if (orderNo == null) {
            throw new IllegalArgumentException("주문번호가 없습니다.");
        }

        return ordersDAO.selectOrderCompleteItems(orderNo);
    }

    @Transactional(readOnly = true)
    public DeliveryDTO selectOrderCompleteDelivery(Long orderNo) {

        if (orderNo == null) {
            throw new IllegalArgumentException("주문번호가 없습니다.");
        }

        return ordersDAO.selectOrderCompleteDelivery(orderNo);
    }
}
