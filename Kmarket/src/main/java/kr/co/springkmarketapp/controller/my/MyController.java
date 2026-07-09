package kr.co.springkmarketapp.controller.my;

import kr.co.springkmarketapp.config.MyUserDetails;
import kr.co.springkmarketapp.dto.common.PageResponseDTO;
import kr.co.springkmarketapp.dto.cs.QnaDTO;
import kr.co.springkmarketapp.dto.member.MemberDTO;
import kr.co.springkmarketapp.dto.member.SellerProfileDTO;
import kr.co.springkmarketapp.dto.my.MemberPointDTO;
import kr.co.springkmarketapp.dto.order.OrderItemDTO;
import kr.co.springkmarketapp.dto.product.ProductReviewDTO;
import kr.co.springkmarketapp.service.my.MyService;
import kr.co.springkmarketapp.util.PageHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MyController {

    private final MyService myService;

    @GetMapping({"/my", "/my/home"})
    public String home(@AuthenticationPrincipal MyUserDetails user,
                       Model model) {

        Integer memberNo = user.getMember().getMemberNo();

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
        model.addAttribute("orderCount", myService.selectOrderCount(memberNo));
        model.addAttribute("couponCount", myService.selectCouponCount(memberNo));
        model.addAttribute("point", myService.selectPoint(memberNo));
        model.addAttribute("qnaCount", myService.selectQnaCount(memberNo));

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
    public String registerQna(@AuthenticationPrincipal MyUserDetails user,
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
            @AuthenticationPrincipal MyUserDetails user,
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

    // 메뉴1 - 전체 주문 내역
    @GetMapping("/my/order")
    public String order(
            @AuthenticationPrincipal MyUserDetails user,
            @RequestParam(defaultValue = "1") int pg,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            Model model) {

        Integer memberNo = user.getMember().getMemberNo();

        int total = myService.selectOrderCount(memberNo, startDate, endDate);

        PageHandler page = new PageHandler(pg, total, 10);

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

        model.addAttribute("orderCount", myService.selectOrderCount(memberNo));
        model.addAttribute("couponCount", myService.selectCouponCount(memberNo));
        model.addAttribute("point", myService.selectPoint(memberNo));
        model.addAttribute("qnaCount", myService.selectQnaCount(memberNo));
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);

        return "my/order";
    }

    // 메뉴2 - 포인트 내역
    @GetMapping("/my/point")
    public String point(@AuthenticationPrincipal MyUserDetails user,
                        @RequestParam(defaultValue = "1") int pg,
                        Model model) {

        Integer memberNo = user.getMember().getMemberNo();

        int total = myService.selectPointCount(memberNo);

        PageHandler page = new PageHandler(pg, total, 10);

        model.addAttribute("page", page);
        model.addAttribute("pointList",
                myService.selectPointList(memberNo, page.getOffset()));

        return "my/point";
    }

    @GetMapping("/my/coupon")
    public String coupon(@AuthenticationPrincipal MyUserDetails user,
                         @RequestParam(defaultValue = "1") int pg,
                         Model model){

        Integer memberNo = user.getMember().getMemberNo();

        int total = myService.selectCouponCount(memberNo);

        PageHandler page = new PageHandler(pg, total, 10);

        model.addAttribute("page", page);
        model.addAttribute("couponList",
                myService.selectCouponList(memberNo, page.getOffset(), 10));

        return "my/coupon";
    }

    @GetMapping("/my/review")
    public String review(@AuthenticationPrincipal MyUserDetails user,
                         @RequestParam(defaultValue = "1") int pg,
                         Model model) {

        Integer memberNo = user.getMember().getMemberNo();

        int total = myService.selectReviewCount(memberNo);

        PageHandler page = new PageHandler(pg, total, 10);

        model.addAttribute("page", page);
        model.addAttribute("reviewList",
                myService.selectReviewList(memberNo, page.getOffset()));

        return "my/review";
    }

    @GetMapping("/my/qna")
    public String qna(@AuthenticationPrincipal MyUserDetails user,
                      @RequestParam(defaultValue = "1") int pg,
                      Model model) {

        Integer memberNo = user.getMember().getMemberNo();

        int total = myService.selectQnaCount(memberNo);

        PageHandler page = new PageHandler(pg, total, 10);

        model.addAttribute("page", page);
        model.addAttribute("qnaList",
                myService.selectQnaList(memberNo, page.getOffset(), 10));

        return "my/qna";
    }



    @GetMapping("/my/info")
    public String setting(@AuthenticationPrincipal MyUserDetails user,
                          Model model){

        Integer memberNo = user.getMember().getMemberNo();

        model.addAttribute("member",
                myService.selectMemberSetting(memberNo));

        return "my/info";
    }

    @PostMapping("/my/setting")
    @ResponseBody
    public String updateSetting(@AuthenticationPrincipal MyUserDetails user,
                                @RequestBody MemberDTO dto){

        dto.setMemberNo(user.getMember().getMemberNo());

        myService.updateMemberSetting(dto);

        return "success";
    }

}