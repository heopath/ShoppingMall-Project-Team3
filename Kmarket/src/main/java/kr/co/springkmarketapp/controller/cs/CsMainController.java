package kr.co.springkmarketapp.controller.cs;

import kr.co.springkmarketapp.service.cs.FaqService;
import kr.co.springkmarketapp.service.cs.NoticeService;
import kr.co.springkmarketapp.service.cs.QnaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class CsMainController {

    private final NoticeService noticeService;
    private final FaqService faqService;
    private final QnaService qnaService;

    @GetMapping({"/cs", "/cs/index"})
    public String index() {
        return "cs/index";
    }
}
