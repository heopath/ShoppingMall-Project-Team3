package kr.co.springkmarketapp.controller.cs.faq;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CsFaqController {

    @GetMapping("/cs/faq/list")
    public String faqList() {
        return "cs/faq/list";
    }

    @GetMapping("/cs/faq/view")
    public String faqView() {
        return "cs/faq/view";
    }

}
