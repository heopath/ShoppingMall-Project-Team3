package kr.co.springkmarketapp.service.order;

import kr.co.springkmarketapp.dao.admin.AdminOrderDAO;
import kr.co.springkmarketapp.dao.order.DeliveryDAO;
import kr.co.springkmarketapp.dto.order.DeliveryDTO;
import kr.co.springkmarketapp.dto.order.DeliveryDetailDTO;
import kr.co.springkmarketapp.service.notification.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryDAO deliveryDAO;
    private final NotificationService notificationService;

    public int insertDelivery(DeliveryDTO deliveryDTO) {
        return deliveryDAO.insertDelivery(deliveryDTO);
    }

    public DeliveryDTO selectDelivery(Integer deliveryNo) {
        return deliveryDAO.selectDelivery(deliveryNo);
    }

    public List<DeliveryDTO> selectDeliveryList() {
        return deliveryDAO.selectDeliveryAll();
    }

    public int updateDelivery(DeliveryDTO deliveryDTO) {
        return deliveryDAO.updateDelivery(deliveryDTO);
    }

    public int deleteDelivery(Integer deliveryNo) {
        return deliveryDAO.deleteDelivery(deliveryNo);
    }

    public Map<String, Object> getDeliveryList(int page, String searchType, String keyword) {
        int size = 10;
        int offset = (page - 1) * size;

        List<DeliveryDTO> list = deliveryDAO.selectDeliveryList(offset, size, searchType, keyword);
        int total = deliveryDAO.selectDeliveryCount(searchType, keyword);
        int totalPages = (int) Math.ceil((double) total / size);

        Map<String, Object> result = new HashMap<>();
        result.put("list", list);
        result.put("totalPages", totalPages);
        return result;
    }

    public DeliveryDetailDTO getDeliveryDetail(int deliveryNo) {
        DeliveryDetailDTO detail = deliveryDAO.selectDeliveryDetail(deliveryNo);
        detail.setItems(deliveryDAO.selectDeliveryItems(deliveryNo));
        return detail;
    }

    public List<DeliveryDetailDTO> getDeliveryDetailsByOrderNo(Long orderNo) {

        List<DeliveryDetailDTO> deliveries = deliveryDAO.selectDeliveryDetailsByOrderNo(orderNo);

        for (DeliveryDetailDTO delivery : deliveries) {
            delivery.setItems(deliveryDAO.selectDeliveryItems(delivery.getDeliveryNo()));
        }

        return deliveries;
    }

    @Transactional
    public void updateInvoiceByDeliveryNo(Integer deliveryNo,
                                          String courier,
                                          String invoiceNo,
                                          String memo) {

        if (deliveryNo == null) {
            throw new IllegalArgumentException("배송번호가 없습니다.");
        }

        if (courier == null || courier.isBlank()) {
            throw new IllegalArgumentException("택배사를 선택하세요.");
        }

        if (invoiceNo == null || invoiceNo.isBlank()) {
            throw new IllegalArgumentException("송장번호를 입력하세요.");
        }

        int result = deliveryDAO.updateInvoiceByDeliveryNo(
                deliveryNo,
                courier,
                invoiceNo,
                memo
        );

        if (result != 1) {
            throw new IllegalArgumentException("배송정보 수정에 실패했습니다.");
        }

        notificationService.createDeliveryShipping(deliveryNo);
    }

    @Transactional
    public void completeArrivedDeliveries() {
        List<Integer> deliveryNos = deliveryDAO.selectShippingDeliveryNosForCompletion();

        for (Integer deliveryNo : deliveryNos) {
            if (deliveryDAO.updateDeliveryComplete(deliveryNo) == 1) {
                notificationService.createDeliveryComplete(deliveryNo);
            }
        }
    }

    // 마이페이지(배송 조회 모달) - 주문번호로 배송 상태와 메시지 생성
    public Map<String, Object> getDeliveryInfoByOrderNo(Long orderNo) {
        DeliveryDTO delivery = deliveryDAO.selectDeliveryByOrderNo(orderNo);
        Map<String, Object> result = new HashMap<>();

        if (delivery == null) {
            result.put("status", "정보없음");
            result.put("message", "배송 정보를 찾을 수 없습니다.");
            result.put("timeline", new ArrayList<>());
            return result;
        }

        String status = delivery.getDeliveryStatus();
        result.put("status", status);
        result.put("message", getMessageByStatus(status));

        List<Map<String, String>> timeline = new ArrayList<>();
        LocalDateTime base = delivery.getReceiptDate();

        // base가 null일 경우를 대비해 null 체크를 해주면 더 안전합니다.
        if (base != null) {
            // 1. 공통 로그
            addTimeline(timeline, "결제완료", base);

            // 2. 배송준비중일 때 (집하완료는 배송중부터 보여줌)
            if ("배송준비".equals(status) || "배송중".equals(status) || "배송완료".equals(status)) {
                addTimeline(timeline, "상품 포장 완료", base.plusHours(2));
            }

            // 3. 배송중/완료일 때만 집하 완료 로그 표시
            if ("배송중".equals(status) || "배송완료".equals(status)) {
                addTimeline(timeline, "택배사 집하 완료", base.plusHours(5));
                addTimeline(timeline, "옥천HUB 간선상차", base.plusHours(12));
            }

            // 4. 배송중일 때만 추가 이동 경로 표시
            if ("배송중".equals(status)) {
                addTimeline(timeline, "부산지점 도착", base.plusHours(20));
            }

            // 5. 배송 완료 시점
            if ("배송완료".equals(status)) {
                addTimeline(timeline, "부산지점 도착", base.plusHours(20));
                addTimeline(timeline, "배송 완료", delivery.getDeliveredDate());
            }
        }

        result.put("timeline", timeline);
        return result;
    }

    private void addTimeline(List<Map<String, String>> list, String s, LocalDateTime d) {
        if (d != null) {
            Map<String, String> map = new HashMap<>();
            map.put("step", s);
            map.put("time", d.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            list.add(map);
        }
    }

    // 상태값에 따른 메시지를 반환하는 메서드
    private String getMessageByStatus(String status) {
        if (status == null) return "주문 정보를 확인 중입니다.";

        return switch (status) {
            case "결제완료" -> "상품을 준비 중입니다.";
            case "배송준비" -> "상품 포장 및 발송 준비 중입니다.";
            case "배송중"   -> "상품이 고객님께 이동 중입니다.";
            case "배송완료" -> "배송이 완료되었습니다.";
            default -> "주문 상태: " + status;
        };
    }

}
