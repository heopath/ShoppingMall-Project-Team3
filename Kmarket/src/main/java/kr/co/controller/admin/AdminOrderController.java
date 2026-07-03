package kr.co.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminOrderController {

    @GetMapping("/admin/order/list")
    public String list() {
        return "admin/order/list";
    }

    @GetMapping("/admin/order/delivery")
    public String delivery() {
        return "admin/order/delivery";
    }
}
