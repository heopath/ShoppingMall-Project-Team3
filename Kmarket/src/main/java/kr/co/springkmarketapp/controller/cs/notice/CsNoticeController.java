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


//    noticeType이없어서 html에서 세부목록 출력불가능 부분 수정
    @GetMapping("/cs/notice/list")
    public String noticeList(
            @RequestParam(defaultValue = "1") int pg,
            @RequestParam(required = false) String noticeType,
            Model model) {

        List<NoticeDTO> allNoticeList = noticeService.selectNoticeList();

        if (noticeType != null && !noticeType.trim().isEmpty()) {
            allNoticeList = allNoticeList.stream()
                    .filter(notice -> noticeType.equals(notice.getNoticeType()))
                    .toList();
        }

        int total = allNoticeList.size();

        PageHandler page = new PageHandler(pg, total, 10);

        int fromIndex = Math.min(page.getOffset(), allNoticeList.size());
        int toIndex = Math.min(fromIndex + 10, allNoticeList.size());

        List<NoticeDTO> noticeDTOList = allNoticeList.subList(fromIndex, toIndex);

        model.addAttribute("noticeDTOList", noticeDTOList);
        model.addAttribute("page", page);
        model.addAttribute("noticeType", noticeType);

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
