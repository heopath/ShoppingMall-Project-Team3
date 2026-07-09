package kr.co.springkmarketapp.controller.product;

import kr.co.springkmarketapp.config.MyUserDetails;
import kr.co.springkmarketapp.dto.common.PageRequestDTO;
import kr.co.springkmarketapp.dto.common.PageResponseDTO;
import kr.co.springkmarketapp.dto.coupon.CouponDTO;
import kr.co.springkmarketapp.dto.coupon.CouponIssueRequestDTO;
import kr.co.springkmarketapp.dto.order.*;
import kr.co.springkmarketapp.dto.product.CategoryDTO;
import kr.co.springkmarketapp.dto.product.ProductListDTO;
import kr.co.springkmarketapp.dto.product.ProductViewDTO;
import kr.co.springkmarketapp.service.admin.BannerService;
import kr.co.springkmarketapp.service.coupon.CouponService;
import kr.co.springkmarketapp.service.member.SellerProfileService;
import kr.co.springkmarketapp.service.order.CartService;
import kr.co.springkmarketapp.service.order.OrdersService;
import kr.co.springkmarketapp.service.product.CategoryService;
import kr.co.springkmarketapp.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final BannerService bannerService;
    private final SellerProfileService sellerProfileService;
    private final OrdersService ordersService;
    private final CouponService couponService;

    // 장바구니 Service 추가
    private final CartService cartService;

    // aside 및 광고 부분 공통 처리
    private void addCommonModel(Model model) {
        model.addAttribute("topBanner", bannerService.getTopBanner());
        model.addAttribute("categoryTree", categoryService.getCategoryTree());
        model.addAttribute("bestProducts", productService.getBestProducts());
    }

    private CategoryDTO addCategoryModel(int cateNo, Model model) {

        CategoryDTO currentCategory = categoryService.getCategory(cateNo);

        if (currentCategory == null) {
            return null;
        }

        CategoryDTO parentCategory = null;

        if (currentCategory.getParentNo() != null) {
            parentCategory =
                    categoryService.getCategory(currentCategory.getParentNo());
        }

        model.addAttribute("currentCategory", currentCategory);
        model.addAttribute("parentCategory", parentCategory);

        return currentCategory;
    }

    @GetMapping("/product/list")
    public String list(PageRequestDTO pageRequestDTO, Model model) {

        CategoryDTO currentCategory =
                addCategoryModel(pageRequestDTO.getCateNo(), model);

        if (currentCategory == null) {
            return "redirect:/";
        }

        addCommonModel(model);

        PageResponseDTO<ProductListDTO> pageResponseDTO =
                productService.getProductsByCategory(pageRequestDTO);

        model.addAttribute("pageResponseDTO", pageResponseDTO);

        return "product/list";
    }

    @GetMapping("/product/view")
    public String view(
            @RequestParam("productNo") int productNo,
            @RequestParam(value = "reviewPage", defaultValue = "1") int reviewPage,
            @AuthenticationPrincipal MyUserDetails userDetails,
            Model model
    ) {

        addCommonModel(model);

        ProductViewDTO product =
                productService.getProductView(productNo);

        if (product == null) {
            return "redirect:/";
        }

        CategoryDTO currentCategory =
                addCategoryModel(product.getCateNo(), model);

        CouponDTO sellerProductCoupon =
                couponService.getSellerProductCouponPreview(product.getSellerNo());

        boolean sellerCouponIssued = false;

        if (userDetails != null && sellerProductCoupon != null) {
            Integer memberNo = userDetails.getMember().getMemberNo();

            sellerCouponIssued =
                    couponService.hasIssuedSellerProductCoupon(
                            memberNo,
                            product.getSellerNo()
                    );
        }


        if (currentCategory == null) {
            return "redirect:/";
        }

        PageRequestDTO reviewPageRequestDTO = new PageRequestDTO();
        reviewPageRequestDTO.setPage(Math.max(reviewPage, 1));
        reviewPageRequestDTO.setSize(5);

        model.addAttribute("product", product);
        model.addAttribute(
                "optionGroups",
                productService.getProductOptionGroups(productNo)
        );
        model.addAttribute(
                "seller",
                sellerProfileService.selectSellerProfile(product.getSellerNo())
        );
        model.addAttribute(
                "detailImages",
                productService.getDetailImages(productNo)
        );
        model.addAttribute(
                "notice",
                productService.getProductNotice(productNo)
        );
        model.addAttribute(
                "reviewPageResponseDTO",
                productService.getProductReviews(
                        productNo,
                        reviewPageRequestDTO
                )
        );
        model.addAttribute("sellerProductCoupon", sellerProductCoupon);
        model.addAttribute("sellerCouponIssued", sellerCouponIssued);

        return "product/view";
    }

    // 쿠본 발급 API
    @PostMapping("/product/coupon/issue")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> issueSellerCoupon(
            @AuthenticationPrincipal MyUserDetails userDetails,
            @RequestBody CouponIssueRequestDTO request
    ) {
        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    Map.of(
                            "result", false,
                            "message", "로그인이 필요합니다."
                    )
            );
        }

        try {
            Integer memberNo = userDetails.getMember().getMemberNo();

            couponService.issueSellerProductCoupon(
                    memberNo,
                    request.getSellerNo()
            );

            return ResponseEntity.ok(
                    Map.of(
                            "result", true,
                            "message", "쿠폰이 발급되었습니다."
                    )
            );

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(
                    Map.of(
                            "result", false,
                            "message", e.getMessage()
                    )
            );
        }
    }

    /*
     * 장바구니 화면
     * GET /product/cart
     */
    @GetMapping("/product/cart")
    public String cart(Model model) {

        addCommonModel(model);

        return "product/cart";
    }

    @GetMapping("/product/cart/items")
    public ResponseEntity<?> getCartItems(
            @AuthenticationPrincipal MyUserDetails userDetails
    ) {

        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    "로그인이 필요합니다."
            );
        }

        Integer memberNo = userDetails.getMember().getMemberNo();

        List<CartListDTO> cartItems =
                cartService.selectCartListByMemberNo(memberNo);

        return ResponseEntity.ok(cartItems);
    }

    /*
     * 장바구니 DB 저장
     * POST /product/cart
     */
    @PostMapping("/product/cart")
    public ResponseEntity<Map<String, Object>> addCart(
            @AuthenticationPrincipal MyUserDetails userDetails,
            @RequestBody CartAddRequestDTO request
    ) {

        /*
         * Security에서 /product/cart를 authenticated()로 막았으므로
         * 일반적으로 여기까지 비로그인 사용자가 들어오지는 않는다.
         * 그래도 API 안전성 때문에 한 번 더 검사한다.
         */
        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    Map.<String, Object>of(
                            "result", false,
                            "message", "로그인이 필요합니다."
                    )
            );
        }

        try {
            Integer memberNo =
                    userDetails.getMember().getMemberNo();

            CartDTO cartDTO =
                    cartService.addCart(memberNo, request);

            return ResponseEntity.ok(
                    Map.<String, Object>of(
                            "result", true,
                            "message", "장바구니에 담았습니다.",
                            "cartNo", cartDTO.getCartNo(),
                            "quantity", cartDTO.getQuantity()
                    )
            );

        } catch (IllegalArgumentException e) {

            return ResponseEntity.badRequest().body(
                    Map.<String, Object>of(
                            "result", false,
                            "message", e.getMessage()
                    )
            );
        }
    }

    /*
     * 장바구니 수량 변경
     * PATCH /product/cart/{cartNo}
     */
    @PatchMapping("/product/cart/{cartNo}")
    public ResponseEntity<Map<String, Object>> updateCartQuantity(
            @AuthenticationPrincipal MyUserDetails userDetails,
            @PathVariable Integer cartNo,
            @RequestBody CartQuantityUpdateRequestDTO request
    ) {
        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    Map.of(
                            "result", false,
                            "message", "로그인이 필요합니다."
                    )
            );
        }

        try {
            Integer memberNo = userDetails.getMember().getMemberNo();

            CartDTO cartDTO = cartService.updateCartQuantity(
                    memberNo,
                    cartNo,
                    request.getQuantity()
            );

            return ResponseEntity.ok(
                    Map.of(
                            "result", true,
                            "quantity", cartDTO.getQuantity()
                    )
            );

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(
                    Map.of(
                            "result", false,
                            "message", e.getMessage()
                    )
            );
        }
    }

    /*
     * 선택 장바구니 삭제
     * DELETE /product/cart
     */
    @DeleteMapping("/product/cart")
    public ResponseEntity<Map<String, Object>> deleteSelectedCart(
            @AuthenticationPrincipal MyUserDetails userDetails,
            @RequestBody CartDeleteRequestDTO request
    ) {
        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    Map.of(
                            "result", false,
                            "message", "로그인이 필요합니다."
                    )
            );
        }

        try {
            Integer memberNo = userDetails.getMember().getMemberNo();

            int deletedCount = cartService.deleteSelectedCarts(
                    memberNo,
                    request.getCartNos()
            );

            return ResponseEntity.ok(
                    Map.of(
                            "result", true,
                            "deletedCount", deletedCount
                    )
            );

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(
                    Map.of(
                            "result", false,
                            "message", e.getMessage()
                    )
            );
        }
    }

    @GetMapping("/product/order")
    public String order(
            @AuthenticationPrincipal MyUserDetails userDetails,
            @RequestParam(name = "cartNo", required = false) List<Integer> cartNos,
            Model model
    ) {
        if (userDetails == null) {
            return "redirect:/member/login";
        }

        if (cartNos == null || cartNos.isEmpty()) {
            return "redirect:/product/cart";
        }

        List<Integer> selectedCartNos = cartNos.stream()
                .filter(Objects::nonNull)
                .distinct()
                .toList();

        if (selectedCartNos.isEmpty()) {
            return "redirect:/product/cart";
        }

        Integer memberNo = userDetails.getMember().getMemberNo();

        List<CartListDTO> orderItems = cartService.selectOrderItems(
                memberNo,
                selectedCartNos
        );

        /*
         * URL로 넘긴 cartNo가 실제 로그인 회원의 장바구니인지 검증
         * 다른 회원의 cartNo를 억지로 넣어도 주문 불가
         */
        if (orderItems.size() != selectedCartNos.size()) {
            return "redirect:/product/cart";
        }

        addCommonModel(model);

        model.addAttribute("orderItems", orderItems);
        model.addAttribute("selectedCartNos", selectedCartNos);

        // 회원정보 출력을 위한 모델 참조
        model.addAttribute("loginMember", userDetails.getMember());

        // 쿠폰 정보 참조
        OrderDiscountDTO discountInfo = ordersService.getOrderDiscountInfo(memberNo);
        model.addAttribute("discountInfo", discountInfo);

        return "product/order";
    }

    @PostMapping("/product/order")
    public ResponseEntity<Map<String, Object>> createOrder(
            @AuthenticationPrincipal MyUserDetails userDetails,
            @RequestBody OrderCreateRequestDTO request
    ) {
        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    Map.of(
                            "result", false,
                            "message", "로그인이 필요합니다."
                    )
            );
        }

        try {
            Integer memberNo = userDetails.getMember().getMemberNo();

            String memberGrade = String.valueOf(
                    userDetails.getMember().getGrade()
            );

            Long orderNo = ordersService.createOrder(
                    memberNo,
                    memberGrade,
                    request
            );

            return ResponseEntity.ok(
                    Map.of(
                            "result", true,
                            "orderNo", orderNo
                    )
            );

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(
                    Map.of(
                            "result", false,
                            "message", e.getMessage()
                    )
            );
        }
    }

    // 상품상세 -> 바로구매
    @PostMapping("/product/order/direct")
    @ResponseBody
    public Map<String, Object> directOrder(
            @RequestBody DirectOrderRequestDTO request,
            @AuthenticationPrincipal MyUserDetails userDetails
    ) {
        if (userDetails == null) {
            return Map.of(
                    "status", "fail",
                    "message", "로그인이 필요합니다."
            );
        }

        Integer memberNo = userDetails.getMember().getMemberNo();

        Integer cartNo = cartService.createDirectCart(memberNo, request);

        return Map.of(
                "status", "success",
                "cartNo", cartNo,
                "redirectUrl", "/product/order?cartNo=" + cartNo
        );
    }

    @GetMapping("/product/complete")
    public String complete(
            @AuthenticationPrincipal MyUserDetails userDetails,
            @RequestParam("orderNo") Long orderNo,
            Model model
    ) {
        if (userDetails == null) {
            return "redirect:/member/login";
        }

        Integer memberNo = userDetails.getMember().getMemberNo();

        OrdersDTO order = ordersService.selectOrderComplete(memberNo, orderNo);

        if (order == null) {
            return "redirect:/";
        }

        List<OrderItemDTO> orderItems =
                ordersService.selectOrderCompleteItems(orderNo);

        DeliveryDTO delivery =
                ordersService.selectOrderCompleteDelivery(orderNo);

        addCommonModel(model);

        model.addAttribute("order", order);
        model.addAttribute("orderItems", orderItems);
        model.addAttribute("delivery", delivery);

        return "product/complete";
    }

    @GetMapping("/product/search")
    public String search(PageRequestDTO pageRequestDTO, Model model) {

        String keyword = pageRequestDTO.getKeyword();

        if (keyword == null || keyword.trim().isEmpty()) {
            return "redirect:/";
        }

        pageRequestDTO.setKeyword(keyword.trim());

        // 정렬값 기본값
        if (pageRequestDTO.getSort() == null || pageRequestDTO.getSort().isBlank()) {
            pageRequestDTO.setSort("sold");
        }

        /*
         * 상품명/상품설명 둘 다 선택 안 한 경우
         * 기본값은 상품명 검색
         */
        boolean searchName = Boolean.TRUE.equals(pageRequestDTO.getSearchName());
        boolean searchDesc = Boolean.TRUE.equals(pageRequestDTO.getSearchDesc());

        if (!searchName && !searchDesc) {
            pageRequestDTO.setSearchName(true);
            pageRequestDTO.setSearchDesc(false);
        }

        // 음수 가격 방어
        if (pageRequestDTO.getMinPrice() != null && pageRequestDTO.getMinPrice() < 0) {
            pageRequestDTO.setMinPrice(null);
        }

        if (pageRequestDTO.getMaxPrice() != null && pageRequestDTO.getMaxPrice() < 0) {
            pageRequestDTO.setMaxPrice(null);
        }

        addCommonModel(model);

        PageResponseDTO<ProductListDTO> pageResponseDTO =
                productService.getProductsBySearch(pageRequestDTO);

        model.addAttribute("pageResponseDTO", pageResponseDTO);
        model.addAttribute("keyword", pageRequestDTO.getKeyword());

        model.addAttribute("searchName", pageRequestDTO.getSearchName());
        model.addAttribute("searchDesc", pageRequestDTO.getSearchDesc());
        model.addAttribute("minPrice", pageRequestDTO.getMinPrice());
        model.addAttribute("maxPrice", pageRequestDTO.getMaxPrice());

        return "product/search";
    }
}