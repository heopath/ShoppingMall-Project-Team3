package kr.co.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminCsController {

    // 공지사항
    @GetMapping("/admin/cs/notice/list")
    public String noticeList() {
        return "admin/cs/notice/list";
    }

    @GetMapping("/admin/cs/notice/view")
    public String noticeView() {
        return "admin/cs/notice/view";
    }

    @GetMapping("/admin/cs/notice/write")
    public String noticeWrite() {
        return "admin/cs/notice/write";
    }

    @GetMapping("/admin/cs/notice/modify")
    public String noticeModify() {
        return "admin/cs/notice/modify";
    }

    // 자주묻는질문
    @GetMapping("/admin/cs/faq/list")
    public String faqList() {
        return "admin/cs/faq/list";
    }

    @GetMapping("/admin/cs/faq/view")
    public String faqView() {
        return "admin/cs/faq/view";
    }

    @GetMapping("/admin/cs/faq/write")
    public String faqWrite() {
        return "admin/cs/faq/write";
    }

    @GetMapping("/admin/cs/faq/modify")
    public String faqModify() {
        return "admin/cs/faq/modify";
    }

    // 문의하기
    @GetMapping("/admin/cs/qna/list")
    public String qnaList() {
        return "admin/cs/qna/list";
    }

    @GetMapping("/admin/cs/qna/view")
    public String qnaView() {
        return "admin/cs/qna/view";
    }

    @GetMapping("/admin/cs/qna/reply")
    public String qnaReply() {
        return "admin/cs/qna/reply";
    }

    // 채용관리
    @GetMapping("/admin/cs/recruit/list")
    public String recruitList() {
        return "admin/cs/recruit/list";
    }
}
