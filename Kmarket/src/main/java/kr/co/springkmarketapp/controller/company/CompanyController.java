package kr.co.springkmarketapp.controller.company;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CompanyController {

    @GetMapping({"/company", "/company/index"})
    public String index() {
        return "company/index";
    }

    @GetMapping("/company/culture")
    public String culture() {
        return "company/culture";
    }

    @GetMapping("/company/media")
    public String media() {
        return "company/media";
    }

    @GetMapping("/company/recruit")
    public String recruit() {
        return "company/recruit";
    }

    @GetMapping("/company/story")
    public String story() {
        return "company/story";
    }
}
