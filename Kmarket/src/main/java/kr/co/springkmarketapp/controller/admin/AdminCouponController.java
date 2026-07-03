package kr.co.springkmarketapp.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminCouponController {

    @GetMapping("/admin/coupon/list")
    public String list() {
        return "admin/coupon/list";
    }

    @GetMapping("/admin/coupon/issued")
    public String issued() {
        return "admin/coupon/issued";
    }
}
