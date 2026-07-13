package kr.co.springkmarketapp.controller.company;

import kr.co.springkmarketapp.dto.company.RecruitDTO;
import kr.co.springkmarketapp.service.company.RecruitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequiredArgsConstructor
public class CompanyController {

    private final RecruitService recruitService;

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
    public String recruit(@RequestParam(defaultValue = "1") int page,
                          @RequestParam(required = false) String department,
                          Model model) {
        int pageSize = 10;
        int pageBlock = 5;
        int totalCount = recruitService.countPublicRecruitList(department);
        int totalPages = Math.max(1, (int) Math.ceil((double) totalCount / pageSize));
        int currentPage = Math.min(Math.max(page, 1), totalPages);
        int offset = (currentPage - 1) * pageSize;
        int startPage = ((currentPage - 1) / pageBlock) * pageBlock + 1;
        int endPage = Math.min(startPage + pageBlock - 1, totalPages);

        model.addAttribute(
                "recruitList",
                recruitService.selectPublicRecruitList(department, offset, pageSize)
        );
        model.addAttribute("department", department == null ? "" : department.trim());
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("totalCount", totalCount);
        return "company/recruit";
    }

    @GetMapping("/company/recruit/{recruitNo}")
    public String recruitView(@PathVariable Integer recruitNo, Model model) {
        RecruitDTO recruit = recruitService.selectPublicRecruit(recruitNo);

        if (recruit == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "채용공고를 찾을 수 없습니다.");
        }

        model.addAttribute("recruit", recruit);
        return "company/recruit-view";
    }

    @GetMapping("/company/story")
    public String story() {
        return "company/story";
    }
}
