package kr.co.springkmarketapp.controller.cs.faq;

import kr.co.springkmarketapp.dto.cs.FaqDTO;
import kr.co.springkmarketapp.service.cs.FaqService;
import kr.co.springkmarketapp.util.PageHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CsFaqController {

    private final FaqService faqService;

    @GetMapping("/cs/faq/list")
    public String faqList(
            @RequestParam(defaultValue = "1") int pg,
            Model model) {

        int total = faqService.countFaq();

        PageHandler page = new PageHandler(pg, total, 10);

        List<FaqDTO> faqDTOList =
                faqService.selectFaqList(page.getOffset(), 10);

        model.addAttribute("faqDTOList", faqDTOList);
        model.addAttribute("page", page);

        return "cs/faq/list";
    }

    @GetMapping("/cs/faq/view")
    public String faqView(
            int faqNo,
            @RequestParam(defaultValue = "1") int pg,
            Model model) {

        FaqDTO faq = faqService.selectFaq(faqNo);

        model.addAttribute("faq", faq);
        model.addAttribute("pg", pg);

        return "cs/faq/view";
    }

}
