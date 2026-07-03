package kr.co.controller.cs.qna;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CsQnaController {

    @GetMapping("/cs/qna/list")
    public String qnaList() {
        return "cs/qna/list";
    }

    @GetMapping("/cs/qna/view")
    public String qnaView() { return "cs/qna/view";}

    @GetMapping("/cs/qna/write")
    public String qnaWrite() {
        return "cs/qna/write";
    }

}
