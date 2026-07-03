package kr.co.controller;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.dto.ArticleDTO;
import kr.co.dto.FileDTO;
import kr.co.service.ArticleService;
import kr.co.sboard.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Controller
public class ArticleController {

    private final ArticleService articleService;
    private final FileService fileService;

    @GetMapping("/article/list")
    public String list(Model model, @RequestParam(defaultValue = "1") int page){
        log.info(page);

        // 전체 글 갯수
        int total = articleService.getTotal();
        int start = articleService.getStart(page);
        int lastPageNum = articleService.getLastPageNum(total);

        int pageGroupStart = articleService.getPageGroupStart(page);
        int pageGroupEnd = articleService.getPageGroupEnd(page, lastPageNum);

        // 목록 데이터 가져오기
        List<ArticleDTO> dtoList = articleService.getAll(start);

        // 모델 참조
        model.addAttribute("dtoList", dtoList);
        model.addAttribute("lastPageNum", lastPageNum);
        model.addAttribute("total", total);
        model.addAttribute("page", page);
        model.addAttribute("pageGroupStart", pageGroupStart);
        model.addAttribute("pageGroupEnd", pageGroupEnd);

        return "article/list";
    }

    @GetMapping("/article/modify")
    public String modify(){
        return "article/modify";
    }

    @GetMapping("/article/search")
    public String search(){
        return "article/search";
    }

    @GetMapping("/article/view")
    public String view(){
        return "article/view";
    }

    @GetMapping("/article/write")
    public String write(){
        return "article/write";
    }

    @PostMapping("/article/write")
    public String write(ArticleDTO articleDTO, HttpServletRequest req){
        log.info(articleDTO);

        String regip = req.getRemoteAddr();
        articleDTO.setRegip(regip);

        // 파일 업로드
        List<FileDTO> fileList = fileService.upload(articleDTO);

        // 파일 첨부 갯수 초기화
        articleDTO.setFile(fileList.size());

        // 글등록
        articleService.register(articleDTO);

        return "redirect:article/list";
    }
}