package kr.co.springkmarketapp.controller.my;

import kr.co.springkmarketapp.config.LoginUser;
import kr.co.springkmarketapp.dto.common.PageResponseDTO;
import kr.co.springkmarketapp.dto.cs.QnaDTO;
import kr.co.springkmarketapp.dto.member.MemberDTO;
import kr.co.springkmarketapp.dto.member.SellerProfileDTO;
import kr.co.springkmarketapp.dto.my.MemberPointDTO;
import kr.co.springkmarketapp.dto.order.OrderClaimDTO;
import kr.co.springkmarketapp.dto.order.OrderItemDTO;
import kr.co.springkmarketapp.dto.product.ProductReviewDTO;
import kr.co.springkmarketapp.entity.order.Delivery;
import kr.co.springkmarketapp.service.admin.BannerService;
import kr.co.springkmarketapp.service.cs.QnaService;
import kr.co.springkmarketapp.service.my.MyService;
import kr.co.springkmarketapp.service.order.DeliveryService;
import kr.co.springkmarketapp.service.order.OrderClaimService;
import kr.co.springkmarketapp.util.PageHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MyController {

    private final MyService myService;
    private final BannerService bannerService;
    private final QnaService qnaService;
    private final OrderClaimService orderClaimService;
    private final DeliveryService deliveryService;

    @GetMapping({"/my", "/my/home"})
    public String home(@AuthenticationPrincipal LoginUser user,
                       Model model) {

        Integer memberNo = user.getMember().getMemberNo();

        model.addAttribute("topBanner", bannerService.getTopBanner());
        model.addAttribute("my1Banner", bannerService.selectBannerListByPosition("MY1"));

        // 최근 주문내역
        List<OrderItemDTO> orderList =
                myService.selectRecentOrderList(Long.valueOf(memberNo));

        // 포인트 적립내역
        List<MemberPointDTO> pointList =
                myService.selectPointList(memberNo);

        // 상품평
        List<ProductReviewDTO> reviewList =
                myService.selectReviewList(memberNo);

        model.addAttribute("orderList", orderList);
        model.addAttribute("pointList", pointList);
        model.addAttribute("reviewList", reviewList);
        model.addAttribute("qnaList",
                myService.selectQnaList(memberNo));

        model.addAttribute("topBanner", bannerService.getTopBanner());

        model.addAttribute("summary", myService.getMyPageSummary(memberNo));
        return "my/home";
    }

    // 주문 상세 모달
    @GetMapping("/my/order/detail/{orderNo}")
    @ResponseBody
    public OrderItemDTO orderDetail(@PathVariable Long orderNo) {
        return myService.selectOrderDetail(orderNo).get(0);
    }

    // 판매자 모달
    @GetMapping("/my/seller/{sellerNo}")
    @ResponseBody
    public SellerProfileDTO sellerDetail(@PathVariable Integer sellerNo) {

        return myService.selectSellerDetail(sellerNo);

    }

    // 문의하기 카테고리 조회
    @GetMapping("/my/qna/category")
    @ResponseBody
    public List<QnaDTO> qnaCategory() {
        return myService.selectQnaCategory();
    }

    // 문의하기 등록
    @PostMapping("/my/qna")
    @ResponseBody
    public String registerQna(@AuthenticationPrincipal LoginUser user,
                              @RequestBody QnaDTO dto) {

        dto.setMemberNo(user.getMember().getMemberNo());

        myService.insertQna(dto);

        return "success";
    }

    // 구매 확정
    @PostMapping("/my/order/confirm/{orderItemNo}")
    @ResponseBody
    public String confirmOrder(@PathVariable Long orderItemNo) {

        myService.confirmOrder(orderItemNo);

        return "success";
    }

    // 상품평 등록
    @PostMapping("/my/review")
    @ResponseBody
    public String registerReview(
            @AuthenticationPrincipal LoginUser user,
            @ModelAttribute ProductReviewDTO dto,
            @RequestParam(value = "images", required = false) MultipartFile[] images) {

        dto.setMemberNo(user.getMember().getMemberNo());

        myService.insertReview(dto, images);

        return "success";
    }

    // 상품명 조회 (모달 열릴 때)
    @GetMapping("/my/review/{orderItemNo}")
    @ResponseBody
    public ProductReviewDTO reviewInfo(@PathVariable Long orderItemNo) {

        return myService.selectReviewInfo(orderItemNo);
    }

    // 배송 조회 보달
    // 배송 조회 모달 (타임라인 포함)
    @GetMapping("/my/order/deliveryInfo")
    @ResponseBody
    public Map<String, Object> getDeliveryInfo(@RequestParam("orderNo") Long orderNo) {
        return deliveryService.getDeliveryInfoByOrderNo(orderNo);
    }

    // 메뉴1 - 전체 주문 내역
    @GetMapping("/my/order")
    public String order(
            @AuthenticationPrincipal LoginUser user,
            @RequestParam(defaultValue = "1") int pg,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            Model model) {

        Integer memberNo = user.getMember().getMemberNo();

        int total = myService.selectOrderCount(memberNo, startDate, endDate);

        PageHandler page = new PageHandler(pg, total, 10);

        Map<String, Integer> summary = myService.getMyPageSummary(memberNo);
        model.addAttribute("summary", summary);

        model.addAttribute("page", page);

        model.addAttribute("orderList",
                myService.selectOrderPage(
                        memberNo,
                        startDate,
                        endDate,
                        page.getOffset(),
                        10));

        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);


        return "my/order";
    }

    // 메뉴2 - 포인트 내역
    @GetMapping("/my/point")
    public String point(@AuthenticationPrincipal LoginUser user,
                        @RequestParam(defaultValue = "1") int pg,
                        @RequestParam(required = false) String startDate,
                        @RequestParam(required = false) String endDate,
                        Model model) {

        Integer memberNo = user.getMember().getMemberNo();

        // 1. 기간 조건이 포함된 개수 조회
        int total = myService.selectPointCount(memberNo, startDate, endDate);
        PageHandler page = new PageHandler(pg, total, 10);

        Map<String, Integer> summary = myService.getMyPageSummary(memberNo);
        model.addAttribute("summary", summary);
        model.addAttribute("page", page);

        // 2. 기간 조건이 포함된 리스트 조회
        model.addAttribute("pointList", myService.selectPointList(memberNo, startDate, endDate, page.getOffset()));
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);

        return "my/point";
    }

    @GetMapping("/my/coupon")
    public String coupon(@AuthenticationPrincipal LoginUser user,
                         @RequestParam(defaultValue = "1") int pg,
                         Model model){

        Integer memberNo = user.getMember().getMemberNo();

        int total = myService.selectCouponCount(memberNo);

        PageHandler page = new PageHandler(pg, total, 10);

        Map<String, Integer> summary = myService.getMyPageSummary(memberNo);
        model.addAttribute("summary", summary);

        model.addAttribute("page", page);
        model.addAttribute("couponList",
                myService.selectCouponList(memberNo, page.getOffset(), 10));

        return "my/coupon";
    }

    @GetMapping("/my/review")
    public String review(@AuthenticationPrincipal LoginUser user,
                         @RequestParam(defaultValue = "1") int pg,
                         Model model) {

        Integer memberNo = user.getMember().getMemberNo();

        int total = myService.selectReviewCount(memberNo);

        PageHandler page = new PageHandler(pg, total, 10);

        Map<String, Integer> summary = myService.getMyPageSummary(memberNo);
        model.addAttribute("summary", summary);

        model.addAttribute("page", page);
        model.addAttribute("reviewList",
                myService.selectReviewList(memberNo, page.getOffset()));

        return "my/review";
    }

    @GetMapping("/my/qna")
    public String qna(@AuthenticationPrincipal LoginUser user,
                      @RequestParam(defaultValue = "1") int pg,
                      Model model) {

        Integer memberNo = user.getMember().getMemberNo();

        // 1. 전체 문의 개수 조회
        int total = myService.selectQnaCount(memberNo);
        PageHandler page = new PageHandler(pg, total, 10);

        model.addAttribute("summary", myService.getMyPageSummary(memberNo));
        model.addAttribute("page", page);

        // 2. 페이징된 문의 리스트 조회
        model.addAttribute("qnaList", myService.selectQnaList(memberNo, page.getOffset(), 10));

        return "my/qna";
    }

    @GetMapping("/my/qna/detail")
    @ResponseBody
    public QnaDTO qnaDetail(@RequestParam Long qnaNo) {
        return qnaService.selectQna(qnaNo);
    }

    // 나의 설정 - 회원 정보 가져오기
    @GetMapping("/my/info")
    public String setting(@AuthenticationPrincipal LoginUser user,
                          Model model){

        Integer memberNo = user.getMember().getMemberNo();

        Map<String, Integer> summary = myService.getMyPageSummary(memberNo);
        model.addAttribute("summary", summary);

        model.addAttribute("member",
                myService.selectMemberSetting(memberNo));

        return "my/info";
    }

    // 나의 설정 - 회원정보 수정하기
    @PostMapping("/my/setting")
    @ResponseBody
    public String updateSetting(@AuthenticationPrincipal LoginUser user,
                                @RequestBody MemberDTO dto){

        dto.setMemberNo(user.getMember().getMemberNo());

        myService.updateMemberSetting(dto);

        return "success";
    }

    // 나의 설정 - 탈퇴하기
    @PostMapping("/my/withdraw")
    @ResponseBody
    public String withdraw(@AuthenticationPrincipal LoginUser user) {
        myService.deleteMember(user.getMember().getMemberNo());
        // 탈퇴 후에는 세션을 무효화하거나 로그인 페이지로 리다이렉트하는 처리가 필요합니다.
        return "success";
    }

    // 나의 설정 - 회원정보 수정 시 중복체크
    @GetMapping("/my/check")
    @ResponseBody
    public Map<String, Object> check(@RequestParam String type,
                                     @RequestParam String value,
                                     @AuthenticationPrincipal LoginUser user) {

        // 본인 제외하고 검색하기 위해 현재 로그인한 사용자의 번호 가져옴
        Integer memberNo = user.getMember().getMemberNo();

        // 이 메서드 내부에서 DB 쿼리 수행:
        // SELECT COUNT(*) FROM member WHERE (email = #{value} OR hp = #{value}) AND member_no != #{memberNo}
        int count = myService.countMemberByValue(type, value, memberNo);

        Map<String, Object> result = new HashMap<>();
        result.put("count", count);
        return result;
    }

    // 반품 신청 처리
    @PostMapping("/my/order/return")
    @ResponseBody
    public String registerReturn(
            @AuthenticationPrincipal LoginUser user, // 1. 유저 정보 추가
            @RequestParam("orderItemNo") int orderItemNo,
            @RequestParam("returnType") String returnType,
            @RequestParam("reason") String reason,
            @RequestParam(value = "returnFile", required = false) MultipartFile file) {

        try {
            OrderClaimDTO claimDTO = new OrderClaimDTO();
            claimDTO.setOrderItemNo((long)orderItemNo);
            claimDTO.setMemberNo(user.getMember().getMemberNo()); // 2. memberNo 세팅
            claimDTO.setClaimType(returnType);
            claimDTO.setClaimReason(reason);
            claimDTO.setStatus("접수완료");

            orderClaimService.registerClaimWithFile(claimDTO, "반품신청", file);
            return "success";
        } catch (Exception e) {
            e.printStackTrace(); // 에러 확인용
            return "fail";
        }
    }

    // 교환 신청 처리
    @PostMapping("/my/order/exchange")
    @ResponseBody
    public String registerExchange(
            @AuthenticationPrincipal LoginUser user, // 1. 유저 정보 추가
            @RequestParam("orderItemNo") int orderItemNo,
            @RequestParam("exchangeType") String exchangeType,
            @RequestParam("reason") String reason,
            @RequestParam(value = "exchangeFile", required = false) MultipartFile file) {

        try {
            OrderClaimDTO claimDTO = new OrderClaimDTO();
            claimDTO.setOrderItemNo((long)orderItemNo);
            claimDTO.setMemberNo(user.getMember().getMemberNo()); // 2. memberNo 세팅
            claimDTO.setClaimType(exchangeType);
            claimDTO.setClaimReason(reason);
            claimDTO.setStatus("접수완료");

            orderClaimService.registerClaimWithFile(claimDTO, "교환신청", file);
            return "success";
        } catch (Exception e) {
            e.printStackTrace(); // 에러 확인용
            return "fail";
        }
    }
}