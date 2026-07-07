package kr.co.springkmarketapp.controller.cs.notice;

import kr.co.springkmarketapp.dto.cs.NoticeDTO;
import kr.co.springkmarketapp.dto.cs.QnaDTO;
import kr.co.springkmarketapp.service.cs.NoticeService;
import kr.co.springkmarketapp.util.PageHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class CsNoticeController {

    private final NoticeService noticeService;

    @GetMapping("/cs/notice/list")
    public String noticeList(
            @RequestParam(defaultValue = "1") int pg,
            Model model) {

        int total = noticeService.countNotice();

        PageHandler page = new PageHandler(pg, total, 10);

        List<NoticeDTO> noticeDTOList =
                noticeService.selectNoticeListByPage(page.getOffset(), 10);

        model.addAttribute("noticeDTOList", noticeDTOList);
        model.addAttribute("page", page);

        return "cs/notice/list";
    }

    @GetMapping("/cs/notice/view")
    public String noticeView(
            int noticeNo,
            @RequestParam(defaultValue = "1") int pg,
            Model model) {

        NoticeDTO notice = noticeService.selectNotice(noticeNo);

        model.addAttribute("notice", notice);
        model.addAttribute("pg", pg);

        return "cs/notice/view";
    }

}
