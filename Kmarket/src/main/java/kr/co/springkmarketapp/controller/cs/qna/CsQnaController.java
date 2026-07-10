package kr.co.springkmarketapp.controller.cs.qna;

import kr.co.springkmarketapp.config.MyUserDetails;
import kr.co.springkmarketapp.dto.cs.QnaDTO;
import kr.co.springkmarketapp.service.cs.QnaService;
import kr.co.springkmarketapp.util.PageHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CsQnaController {

    private  final QnaService qnaService;

    @GetMapping("/cs/qna/list")
    public String qnaList(
            @RequestParam(defaultValue = "1") int parentNo,
            @RequestParam(defaultValue = "1") int pg,
            Model model) {

        int total = qnaService.countQna(parentNo);

        PageHandler page = new PageHandler(pg, total, 10);

        List<QnaDTO> qnaDTOList =
                qnaService.selectQnaListPage(parentNo, page.getOffset(), 10);

        model.addAttribute("qnaDTOList", qnaDTOList);
        model.addAttribute("page", page);
        model.addAttribute("parentNo", parentNo);

        return "cs/qna/list";
    }

    @GetMapping("/cs/qna/view")
    public String qnaView(
            Long qnaNo,
            @RequestParam(defaultValue = "1") int pg,
            @RequestParam(defaultValue = "1") int parentNo,
            Model model) {

        QnaDTO qna = qnaService.selectQna(qnaNo);

        model.addAttribute("qna", qna);
        model.addAttribute("pg", pg);
        model.addAttribute("parentNo", parentNo);

        return "cs/qna/view";
    }

    @GetMapping("/cs/qna/write")
    public String qnaWrite(@RequestParam int parentNo,
                           Model model) {

        model.addAttribute("parentNo", parentNo);

        model.addAttribute(
                "parentCategoryList",
                qnaService.selectParentCategoryList()
        );

        model.addAttribute(
                "childCategoryList",
                qnaService.selectChildCategoryList(parentNo)
        );

        return "cs/qna/write";
    }

    @PostMapping("/cs/qna/write")
    public String qnaWrite(
            QnaDTO qnaDTO,
            @RequestParam int parentNo,
            Authentication authentication) {

        if (qnaDTO.getCsCateNo() == null ||
                qnaDTO.getTitle() == null || qnaDTO.getTitle().isBlank() ||
                qnaDTO.getContent() == null || qnaDTO.getContent().isBlank()) {

            return "redirect:/cs/qna/write?parentNo=" + parentNo;
        }

        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();

        qnaDTO.setMemberNo(userDetails.getMember().getMemberNo());

        qnaService.insertQna(qnaDTO);

        return "redirect:/cs/qna/list?parentNo=" + parentNo;
    }

}
