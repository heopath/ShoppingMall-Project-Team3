package kr.co.springkmarketapp.controller.cs;

import kr.co.springkmarketapp.dto.cs.NoticeDTO;
import kr.co.springkmarketapp.dto.cs.QnaDTO;
import kr.co.springkmarketapp.service.cs.FaqService;
import kr.co.springkmarketapp.service.cs.NoticeService;
import kr.co.springkmarketapp.service.cs.QnaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CsMainController {

    private final NoticeService noticeService;
    private final QnaService qnaService;

    @GetMapping({"/cs", "/cs/index"})
    public String index(Model model) {

        List<NoticeDTO> noticeDTOList =
                noticeService.selectNoticeList();

        List<QnaDTO> qnaDTOList =
                qnaService.selectQnaList();

        model.addAttribute("noticeDTOList", noticeDTOList);
        model.addAttribute("qnaDTOList", qnaDTOList);

        return "cs/index";
    }
}
