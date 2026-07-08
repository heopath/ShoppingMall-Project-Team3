package kr.co.springkmarketapp.controller.my;

import kr.co.springkmarketapp.config.MyUserDetails;
import kr.co.springkmarketapp.dto.member.SellerProfileDTO;
import kr.co.springkmarketapp.dto.order.OrderItemDTO;
import kr.co.springkmarketapp.service.my.MyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MyController {

    private final MyService myService;

    @GetMapping({"/my", "/my/home"})
    public String home(@AuthenticationPrincipal MyUserDetails user,
                       Model model) {

        Long memberNo = Long.valueOf(user.getMember().getMemberNo());

        List<OrderItemDTO> orderList =
                myService.selectRecentOrderList(memberNo);

        model.addAttribute("orderList", orderList);

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
    public SellerProfileDTO sellerDetail(@PathVariable Integer sellerNo){

        return myService.selectSellerDetail(sellerNo);

    }

    @GetMapping("/my/coupon")
    public String coupon() {
        return "my/coupon";
    }

    @GetMapping("/my/info")
    public String info() {
        return "my/info";
    }

    @GetMapping("/my/order")
    public String order() {
        return "my/order";
    }

    @GetMapping("/my/point")
    public String point() {
        return "my/point";
    }

    @GetMapping("/my/qna")
    public String qna() {
        return "my/qna";
    }

    @GetMapping("/my/review")
    public String review() {
        return "my/review";
    }
}
