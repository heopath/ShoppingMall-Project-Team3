package kr.co.springkmarketapp.controller.admin;

import kr.co.springkmarketapp.dto.company.RecruitDTO;
import kr.co.springkmarketapp.dto.cs.FaqDTO;
import kr.co.springkmarketapp.dto.cs.NoticeDTO;
import kr.co.springkmarketapp.dto.cs.QnaDTO;
import kr.co.springkmarketapp.service.company.RecruitService;
import kr.co.springkmarketapp.service.cs.FaqService;
import kr.co.springkmarketapp.service.cs.NoticeService;
import kr.co.springkmarketapp.service.cs.QnaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class AdminCsController {

    private final NoticeService noticeService;
    private final FaqService faqService;
    private final QnaService qnaService;
    private final RecruitService recruitService;

    @GetMapping("/admin/cs/notice/list")
    public String noticeList(Model model) {
        model.addAttribute("noticeList", noticeService.selectNoticeList());
        return "admin/cs/notice/list";
    }

    @GetMapping("/admin/cs/notice/view")
    public String noticeView(@RequestParam Integer noticeNo, Model model) {
        model.addAttribute("notice", noticeService.selectNotice(noticeNo));
        return "admin/cs/notice/view";
    }

    @GetMapping("/admin/cs/notice/write")
    public String noticeWrite() {
        return "admin/cs/notice/write";
    }

    @PostMapping("/admin/cs/notice/write")
    public String noticeWriteProc(NoticeDTO noticeDTO) {
        noticeDTO.setWriterNo(1);
        noticeService.insertNotice(noticeDTO);
        return "redirect:/admin/cs/notice/list";
    }

    @GetMapping("/admin/cs/notice/modify")
    public String noticeModify(@RequestParam Integer noticeNo, Model model) {
        model.addAttribute("notice", noticeService.selectNotice(noticeNo));
        return "admin/cs/notice/modify";
    }

    @PostMapping("/admin/cs/notice/modify")
    public String noticeModifyProc(NoticeDTO noticeDTO) {
        NoticeDTO old = noticeService.selectNotice(noticeDTO.getNoticeNo());
        if (old != null) {
            noticeDTO.setWriterNo(old.getWriterNo() == null ? 1 : old.getWriterNo());
            noticeDTO.setHit(old.getHit() == null ? 0 : old.getHit());
            noticeService.updateNotice(noticeDTO);
        }
        return "redirect:/admin/cs/notice/view?noticeNo=" + noticeDTO.getNoticeNo();
    }

    @PostMapping("/admin/cs/notice/delete")
    public String noticeDelete(@RequestParam Integer noticeNo) {
        noticeService.deleteNotice(noticeNo);
        return "redirect:/admin/cs/notice/list";
    }

    @GetMapping("/admin/cs/faq/list")
    public String faqList(Model model) {
        model.addAttribute("faqList", faqService.selectFaqList());
        return "admin/cs/faq/list";
    }

    @GetMapping("/admin/cs/faq/view")
    public String faqView(@RequestParam Integer faqNo, Model model) {
        model.addAttribute("faq", faqService.selectFaq(faqNo));
        return "admin/cs/faq/view";
    }

    @GetMapping("/admin/cs/faq/write")
    public String faqWrite() {
        return "admin/cs/faq/write";
    }

    @PostMapping("/admin/cs/faq/write")
    public String faqWriteProc(FaqDTO faqDTO) {
        faqDTO.setWriterNo(1);
        if (faqDTO.getCsCateNo() == null) faqDTO.setCsCateNo(1);
        faqService.insertFaq(faqDTO);
        return "redirect:/admin/cs/faq/list";
    }

    @GetMapping("/admin/cs/faq/modify")
    public String faqModify(@RequestParam Integer faqNo, Model model) {
        model.addAttribute("faq", faqService.selectFaq(faqNo));
        return "admin/cs/faq/modify";
    }

    @PostMapping("/admin/cs/faq/modify")
    public String faqModifyProc(FaqDTO faqDTO) {
        FaqDTO old = faqService.selectFaq(faqDTO.getFaqNo());
        if (old != null) {
            faqDTO.setWriterNo(old.getWriterNo() == null ? 1 : old.getWriterNo());
            faqDTO.setHit(old.getHit() == null ? 0 : old.getHit());
            faqService.updateFaq(faqDTO);
        }
        return "redirect:/admin/cs/faq/view?faqNo=" + faqDTO.getFaqNo();
    }

    @PostMapping("/admin/cs/faq/delete")
    public String faqDelete(@RequestParam Integer faqNo) {
        faqService.deleteFaq(faqNo);
        return "redirect:/admin/cs/faq/list";
    }

    @GetMapping("/admin/cs/qna/list")
    public String qnaList(Model model) {
        model.addAttribute("qnaList", qnaService.selectQnaList());
        return "admin/cs/qna/list";
    }

    @GetMapping("/admin/cs/qna/view")
    public String qnaView(@RequestParam Long qnaNo, Model model) {
        model.addAttribute("qna", qnaService.selectQna(qnaNo));
        return "admin/cs/qna/view";
    }

    @GetMapping("/admin/cs/qna/reply")
    public String qnaReply(@RequestParam Long qnaNo, Model model) {
        model.addAttribute("qna", qnaService.selectQna(qnaNo));
        return "admin/cs/qna/reply";
    }

    @PostMapping("/admin/cs/qna/reply")
    public String qnaReplyProc(@RequestParam Long qnaNo, @RequestParam String answer) {
        QnaDTO qna = qnaService.selectQna(qnaNo);
        if (qna != null) {
            qna.setAnswer(answer);
            qna.setAnswerMemberNo(1);
            qna.setStatus("ANSWERED");
            qna.setAnswerDate(LocalDateTime.now());
            qnaService.updateQna(qna);
        }
        return "redirect:/admin/cs/qna/view?qnaNo=" + qnaNo;
    }

    @PostMapping("/admin/cs/qna/delete")
    public String qnaDelete(@RequestParam Long qnaNo) {
        qnaService.deleteQna(qnaNo);
        return "redirect:/admin/cs/qna/list";
    }

    @GetMapping("/admin/cs/recruit/list")
    public String recruitList(Model model) {
        model.addAttribute("recruitList", recruitService.selectRecruitList());
        return "admin/cs/recruit/list";
    }

    @PostMapping("/admin/cs/recruit/register")
    public String recruitRegister(RecruitDTO recruitDTO) {
        recruitDTO.setWriterNo(1);
        recruitService.insertRecruit(recruitDTO);
        return "redirect:/admin/cs/recruit/list";
    }

    @PostMapping("/admin/cs/recruit/status")
    public String recruitStatus(@RequestParam Integer recruitNo, @RequestParam String status) {
        RecruitDTO recruit = recruitService.selectRecruit(recruitNo);
        if (recruit != null) {
            recruit.setStatus(status);
            recruitService.updateRecruit(recruit);
        }
        return "redirect:/admin/cs/recruit/list";
    }

    @PostMapping("/admin/cs/recruit/delete")
    public String recruitDelete(@RequestParam Integer recruitNo) {
        recruitService.deleteRecruit(recruitNo);
        return "redirect:/admin/cs/recruit/list";
    }
}
