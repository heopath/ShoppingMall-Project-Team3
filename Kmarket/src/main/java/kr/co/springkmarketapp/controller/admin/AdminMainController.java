package kr.co.springkmarketapp.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminMainController {

    @GetMapping("/admin")
    public String index() {
        return "admin/index";
    }
}
