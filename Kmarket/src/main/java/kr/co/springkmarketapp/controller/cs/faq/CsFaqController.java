package kr.co.springkmarketapp.controller.cs.faq;

import kr.co.springkmarketapp.dto.cs.FaqDTO;
import kr.co.springkmarketapp.service.cs.FaqService;
import kr.co.springkmarketapp.util.PageHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CsFaqController {

    private final FaqService faqService;

    @GetMapping("/cs/faq/list")
    public String faqList(Model model){

        Map<String, List<FaqDTO>> faqMap =
                faqService.selectFaqGroup(1);

        model.addAttribute("faqMap", faqMap);

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
