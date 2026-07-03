package kr.co.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminConfigController {

    @GetMapping("/admin/config/basic")
    public String basic() {
        return "admin/config/basic";
    }

    @GetMapping("/admin/config/banner")
    public String banner() {
        return "admin/config/banner";
    }

    @GetMapping("/admin/config/policy")
    public String policy() {
        return "admin/config/policy";
    }

    @GetMapping("/admin/config/category")
    public String category() {
        return "admin/config/category";
    }

    @GetMapping("/admin/config/version")
    public String version() {
        return "admin/config/version";
    }
}
