package kr.co.springkmarketapp.controller.cs.notice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CsNoticeController {

    @GetMapping("/cs/notice/list")
    public String noticeList() {
        return "cs/notice/list";
    }

    @GetMapping("/cs/notice/view")
    public String noticeView() {
        return "cs/notice/view";
    }

}
