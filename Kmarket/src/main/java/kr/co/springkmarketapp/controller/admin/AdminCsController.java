package kr.co.springkmarketapp.controller.admin;

import kr.co.springkmarketapp.dto.company.RecruitDTO;
import kr.co.springkmarketapp.dto.cs.CsCategoryDTO;
import kr.co.springkmarketapp.dto.cs.FaqDTO;
import kr.co.springkmarketapp.dto.cs.NoticeDTO;
import kr.co.springkmarketapp.dto.cs.QnaDTO;
import kr.co.springkmarketapp.service.company.RecruitService;
import kr.co.springkmarketapp.service.cs.CsCategoryService;
import kr.co.springkmarketapp.service.cs.FaqService;
import kr.co.springkmarketapp.service.cs.NoticeService;
import kr.co.springkmarketapp.service.cs.QnaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class AdminCsController {

    private final NoticeService noticeService;
    private final FaqService faqService;
    private final CsCategoryService csCategoryService;
    private final QnaService qnaService;
    private final RecruitService recruitService;

    @GetMapping("/admin/cs/notice/list")
    public String noticeList(@RequestParam(defaultValue = "1") int page,
                             @RequestParam(required = false) String searchType,
                             @RequestParam(required = false) String keyword,
                             Model model) {
        int pageSize = 10;
        int pageBlock = 5;

        List<NoticeDTO> filteredList = filterNoticeList(noticeService.selectNoticeList(), searchType, keyword);
        int totalCount = filteredList.size();
        int totalPages = Math.max(1, (int) Math.ceil((double) totalCount / pageSize));
        int currentPage = Math.min(Math.max(page, 1), totalPages);

        model.addAttribute("noticeList", getPageList(filteredList, currentPage, pageSize));
        model.addAttribute("searchType", searchType);
        model.addAttribute("keyword", keyword);
        addPagination(model, currentPage, totalPages, pageBlock, totalCount);
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
    public String noticeWriteProc(NoticeDTO noticeDTO, RedirectAttributes redirectAttributes) {
        if (isBlank(noticeDTO.getNoticeType())) {
            noticeDTO.setNoticeType("고객서비스");
        }
        noticeDTO.setWriterNo(1);
        noticeService.insertNotice(noticeDTO);
        redirectAttributes.addFlashAttribute("message", "공지사항이 등록되었습니다.");
        return "redirect:/admin/cs/notice/list";
    }

    @GetMapping("/admin/cs/notice/modify")
    public String noticeModify(@RequestParam Integer noticeNo, Model model) {
        model.addAttribute("notice", noticeService.selectNotice(noticeNo));
        return "admin/cs/notice/modify";
    }

    @PostMapping("/admin/cs/notice/modify")
    public String noticeModifyProc(NoticeDTO noticeDTO, RedirectAttributes redirectAttributes) {
        NoticeDTO old = noticeService.selectNotice(noticeDTO.getNoticeNo());
        if (old != null) {
            noticeDTO.setWriterNo(old.getWriterNo() == null ? 1 : old.getWriterNo());
            noticeDTO.setHit(old.getHit() == null ? 0 : old.getHit());
            noticeService.updateNotice(noticeDTO);
            redirectAttributes.addFlashAttribute("message", "공지사항이 수정되었습니다.");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "수정할 공지사항을 찾을 수 없습니다.");
        }
        return "redirect:/admin/cs/notice/view?noticeNo=" + noticeDTO.getNoticeNo();
    }

    @PostMapping("/admin/cs/notice/delete")
    public String noticeDelete(@RequestParam(required = false) List<Integer> noticeNos,
                               @RequestParam(required = false) Integer noticeNo,
                               @RequestParam(required = false, defaultValue = "1") int page,
                               @RequestParam(required = false) String searchType,
                               @RequestParam(required = false) String keyword,
                               RedirectAttributes redirectAttributes) {
        if (noticeNos != null && !noticeNos.isEmpty()) {
            for (Integer no : noticeNos) {
                noticeService.deleteNotice(no);
            }
            redirectAttributes.addFlashAttribute("message", "선택한 공지사항이 삭제되었습니다.");
        } else if (noticeNo != null) {
            noticeService.deleteNotice(noticeNo);
            redirectAttributes.addFlashAttribute("message", "공지사항이 삭제되었습니다.");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "삭제할 공지사항을 선택하세요.");
        }
        return "redirect:" + buildRedirectUrl("/admin/cs/notice/list", page, searchType, keyword);
    }

    @GetMapping("/admin/cs/faq/list")
    public String faqList(@RequestParam(defaultValue = "1") int page,
                          @RequestParam(required = false) String searchType,
                          @RequestParam(required = false) String keyword,
                          Model model) {
        int pageSize = 10;
        int pageBlock = 5;

        List<FaqDTO> filteredList = filterFaqList(faqService.selectFaqList(), searchType, keyword);
        int totalCount = filteredList.size();
        int totalPages = Math.max(1, (int) Math.ceil((double) totalCount / pageSize));
        int currentPage = Math.min(Math.max(page, 1), totalPages);

        addCsCategoryModel(model);
        model.addAttribute("faqList", getPageList(filteredList, currentPage, pageSize));
        model.addAttribute("searchType", searchType);
        model.addAttribute("keyword", keyword);
        addPagination(model, currentPage, totalPages, pageBlock, totalCount);
        return "admin/cs/faq/list";
    }

    @GetMapping("/admin/cs/faq/view")
    public String faqView(@RequestParam Integer faqNo, Model model) {
        model.addAttribute("faq", faqService.selectFaq(faqNo));
        return "admin/cs/faq/view";
    }

    @GetMapping("/admin/cs/faq/write")
    public String faqWrite(Model model) {
        addCsCategoryModel(model);
        return "admin/cs/faq/write";
    }

    @PostMapping("/admin/cs/faq/write")
    public String faqWriteProc(FaqDTO faqDTO, RedirectAttributes redirectAttributes) {
        faqDTO.setWriterNo(1);
        if (faqDTO.getCsCateNo() == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "FAQ 유형을 선택하세요.");
            return "redirect:/admin/cs/faq/write";
        }
        faqService.insertFaq(faqDTO);
        redirectAttributes.addFlashAttribute("message", "자주묻는 질문이 등록되었습니다.");
        return "redirect:/admin/cs/faq/list";
    }

    @GetMapping("/admin/cs/faq/modify")
    public String faqModify(@RequestParam Integer faqNo, Model model) {
        addCsCategoryModel(model);
        model.addAttribute("faq", faqService.selectFaq(faqNo));
        return "admin/cs/faq/modify";
    }

    @PostMapping("/admin/cs/faq/modify")
    public String faqModifyProc(FaqDTO faqDTO, RedirectAttributes redirectAttributes) {
        FaqDTO old = faqService.selectFaq(faqDTO.getFaqNo());
        if (old != null) {
            faqDTO.setWriterNo(old.getWriterNo() == null ? 1 : old.getWriterNo());
            faqDTO.setHit(old.getHit() == null ? 0 : old.getHit());
            faqService.updateFaq(faqDTO);
            redirectAttributes.addFlashAttribute("message", "자주묻는 질문이 수정되었습니다.");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "수정할 FAQ를 찾을 수 없습니다.");
        }
        return "redirect:/admin/cs/faq/view?faqNo=" + faqDTO.getFaqNo();
    }

    @PostMapping("/admin/cs/faq/delete")
    public String faqDelete(@RequestParam(required = false) List<Integer> faqNos,
                            @RequestParam(required = false) Integer faqNo,
                            @RequestParam(required = false, defaultValue = "1") int page,
                            @RequestParam(required = false) String searchType,
                            @RequestParam(required = false) String keyword,
                            RedirectAttributes redirectAttributes) {
        if (faqNos != null && !faqNos.isEmpty()) {
            for (Integer no : faqNos) {
                faqService.deleteFaq(no);
            }
            redirectAttributes.addFlashAttribute("message", "선택한 FAQ가 삭제되었습니다.");
        } else if (faqNo != null) {
            faqService.deleteFaq(faqNo);
            redirectAttributes.addFlashAttribute("message", "FAQ가 삭제되었습니다.");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "삭제할 FAQ를 선택하세요.");
        }
        return "redirect:" + buildRedirectUrl("/admin/cs/faq/list", page, searchType, keyword);
    }

    @GetMapping("/admin/cs/qna/list")
    public String qnaList(@RequestParam(defaultValue = "1") int page,
                          @RequestParam(required = false) String searchType,
                          @RequestParam(required = false) String keyword,
                          @RequestParam(required = false) String status,
                          Model model) {
        int pageSize = 10;
        int pageBlock = 5;

        List<QnaDTO> filteredList = filterQnaList(qnaService.selectQnaList(), searchType, keyword, status);
        int totalCount = filteredList.size();
        int totalPages = Math.max(1, (int) Math.ceil((double) totalCount / pageSize));
        int currentPage = Math.min(Math.max(page, 1), totalPages);

        addCsCategoryModel(model);
        model.addAttribute("qnaList", getPageList(filteredList, currentPage, pageSize));
        model.addAttribute("searchType", searchType);
        model.addAttribute("keyword", keyword);
        model.addAttribute("status", status);
        addPagination(model, currentPage, totalPages, pageBlock, totalCount);
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
    public String qnaReplyProc(@RequestParam Long qnaNo,
                               @RequestParam String answer,
                               RedirectAttributes redirectAttributes) {
        QnaDTO qna = qnaService.selectQna(qnaNo);
        if (qna != null) {
            qna.setAnswer(answer);
            qna.setAnswerMemberNo(1);
            qna.setStatus("답변완료");
            qna.setAnswerDate(LocalDateTime.now());
            qnaService.updateQna(qna);
            redirectAttributes.addFlashAttribute("message", "답변이 등록되었습니다.");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "문의 내용을 찾을 수 없습니다.");
        }
        return "redirect:/admin/cs/qna/view?qnaNo=" + qnaNo;
    }

    @PostMapping("/admin/cs/qna/delete")
    public String qnaDelete(@RequestParam(required = false) List<Long> qnaNos,
                            @RequestParam(required = false) Long qnaNo,
                            @RequestParam(required = false, defaultValue = "1") int page,
                            @RequestParam(required = false) String searchType,
                            @RequestParam(required = false) String keyword,
                            @RequestParam(required = false) String status,
                            RedirectAttributes redirectAttributes) {
        if (qnaNos != null && !qnaNos.isEmpty()) {
            for (Long no : qnaNos) {
                qnaService.deleteQna(no);
            }
            redirectAttributes.addFlashAttribute("message", "선택한 문의가 삭제되었습니다.");
        } else if (qnaNo != null) {
            qnaService.deleteQna(qnaNo);
            redirectAttributes.addFlashAttribute("message", "문의가 삭제되었습니다.");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "삭제할 문의를 선택하세요.");
        }
        return "redirect:" + buildRedirectUrl("/admin/cs/qna/list", page, searchType, keyword, status);
    }

    @GetMapping("/admin/cs/recruit/list")
    public String recruitList(@RequestParam(defaultValue = "1") int page,
                              @RequestParam(required = false) String searchType,
                              @RequestParam(required = false) String keyword,
                              @RequestParam(required = false) String status,
                              Model model) {
        int pageSize = 10;
        int pageBlock = 5;
        int totalCount = recruitService.countRecruitList(searchType, keyword, status);
        int totalPages = Math.max(1, (int) Math.ceil((double) totalCount / pageSize));
        int currentPage = Math.min(Math.max(page, 1), totalPages);
        int offset = (currentPage - 1) * pageSize;

        model.addAttribute("recruitList", recruitService.selectRecruitList(searchType, keyword, status, offset, pageSize));
        model.addAttribute("searchType", searchType);
        model.addAttribute("keyword", keyword);
        model.addAttribute("status", status);
        addPagination(model, currentPage, totalPages, pageBlock, totalCount);
        return "admin/cs/recruit/list";
    }

    @PostMapping("/admin/cs/recruit/register")
    public String recruitRegister(RecruitDTO recruitDTO, RedirectAttributes redirectAttributes) {
        recruitDTO.setWriterNo(1);
        if (isBlank(recruitDTO.getStatus())) {
            recruitDTO.setStatus("모집중");
        }
        recruitService.insertRecruit(recruitDTO);
        redirectAttributes.addFlashAttribute("message", "채용공고가 등록되었습니다.");
        return "redirect:/admin/cs/recruit/list";
    }

    @PostMapping("/admin/cs/recruit/modify")
    public String recruitModify(RecruitDTO recruitDTO,
                                @RequestParam(required = false, defaultValue = "1") int page,
                                @RequestParam(required = false) String searchType,
                                @RequestParam(required = false) String keyword,
                                @RequestParam(required = false) String status,
                                RedirectAttributes redirectAttributes) {
        RecruitDTO old = recruitService.selectRecruit(recruitDTO.getRecruitNo());
        if (old != null) {
            recruitDTO.setWriterNo(old.getWriterNo() == null ? 1 : old.getWriterNo());
            if (isBlank(recruitDTO.getStatus())) {
                recruitDTO.setStatus(old.getStatus() == null ? "모집중" : old.getStatus());
            }
            recruitService.updateRecruit(recruitDTO);
            redirectAttributes.addFlashAttribute("message", "채용공고가 수정되었습니다.");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "수정할 채용공고를 찾을 수 없습니다.");
        }
        return "redirect:" + buildRedirectUrl("/admin/cs/recruit/list", page, searchType, keyword, status);
    }

    @PostMapping("/admin/cs/recruit/status")
    public String recruitStatus(@RequestParam Integer recruitNo,
                                @RequestParam String status,
                                @RequestParam(required = false, defaultValue = "1") int page,
                                @RequestParam(required = false) String searchType,
                                @RequestParam(required = false) String keyword,
                                @RequestParam(required = false) String currentStatus,
                                RedirectAttributes redirectAttributes) {
        RecruitDTO recruit = recruitService.selectRecruit(recruitNo);
        if (recruit != null) {
            recruit.setStatus(status);
            recruitService.updateRecruit(recruit);
            redirectAttributes.addFlashAttribute("message", "채용공고 상태가 변경되었습니다.");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "채용공고를 찾을 수 없습니다.");
        }
        return "redirect:" + buildRedirectUrl("/admin/cs/recruit/list", page, searchType, keyword, currentStatus);
    }

    @PostMapping("/admin/cs/recruit/delete")
    public String recruitDelete(@RequestParam(required = false) List<Integer> recruitNos,
                                @RequestParam(required = false) Integer recruitNo,
                                @RequestParam(required = false, defaultValue = "1") int page,
                                @RequestParam(required = false) String searchType,
                                @RequestParam(required = false) String keyword,
                                @RequestParam(required = false) String status,
                                RedirectAttributes redirectAttributes) {
        if (recruitNos != null && !recruitNos.isEmpty()) {
            recruitService.deleteRecruits(recruitNos);
            redirectAttributes.addFlashAttribute("message", "선택한 채용공고가 삭제되었습니다.");
        } else if (recruitNo != null) {
            recruitService.deleteRecruit(recruitNo);
            redirectAttributes.addFlashAttribute("message", "채용공고가 삭제되었습니다.");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "삭제할 채용공고를 선택하세요.");
        }
        return "redirect:" + buildRedirectUrl("/admin/cs/recruit/list", page, searchType, keyword, status);
    }

    private void addCsCategoryModel(Model model) {
        List<CsCategoryDTO> categoryList = nullToEmpty(csCategoryService.selectCsCategoryList());
        List<CsCategoryDTO> parentCategoryList = categoryList.stream()
                .filter(category -> Objects.equals(category.getDepth(), 1))
                .toList();
        List<CsCategoryDTO> childCategoryList = categoryList.stream()
                .filter(category -> Objects.equals(category.getDepth(), 2))
                .toList();

        model.addAttribute("parentCategoryList", parentCategoryList);
        model.addAttribute("childCategoryList", childCategoryList);
    }

    private void addPagination(Model model, int currentPage, int totalPages, int pageBlock, int totalCount) {
        int startPage = ((currentPage - 1) / pageBlock) * pageBlock + 1;
        int endPage = Math.min(startPage + pageBlock - 1, totalPages);

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("hasPrevPage", currentPage > 1);
        model.addAttribute("hasNextPage", currentPage < totalPages);
        model.addAttribute("totalCount", totalCount);
    }

    private String buildRedirectUrl(String path, int page, String searchType, String keyword) {
        return UriComponentsBuilder.fromPath(path)
                .queryParam("page", page)
                .queryParam("searchType", searchType)
                .queryParam("keyword", keyword)
                .build()
                .encode()
                .toUriString();
    }

    private String buildRedirectUrl(String path, int page, String searchType, String keyword, String status) {
        return UriComponentsBuilder.fromPath(path)
                .queryParam("page", page)
                .queryParam("searchType", searchType)
                .queryParam("keyword", keyword)
                .queryParam("status", status)
                .build()
                .encode()
                .toUriString();
    }

    private List<NoticeDTO> filterNoticeList(List<NoticeDTO> source, String searchType, String keyword) {
        List<NoticeDTO> result = new ArrayList<>();
        for (NoticeDTO notice : nullToEmpty(source)) {
            if (matchesNotice(notice, searchType, keyword)) {
                result.add(notice);
            }
        }
        return result;
    }

    private boolean matchesNotice(NoticeDTO notice, String searchType, String keyword) {
        if (isBlank(keyword)) return true;
        String key = keyword.trim();

        if ("noticeType".equals(searchType)) {
            return contains(notice.getNoticeType(), key);
        }
        if ("title".equals(searchType)) {
            return contains(notice.getTitle(), key);
        }
        if ("content".equals(searchType)) {
            return contains(notice.getContent(), key);
        }
        return contains(notice.getTitle(), key)
                || contains(notice.getContent(), key)
                || contains(notice.getNoticeType(), key);
    }

    private List<FaqDTO> filterFaqList(List<FaqDTO> source, String searchType, String keyword) {
        List<FaqDTO> result = new ArrayList<>();
        for (FaqDTO faq : nullToEmpty(source)) {
            if (matchesFaq(faq, searchType, keyword)) {
                result.add(faq);
            }
        }
        return result;
    }

    private boolean matchesFaq(FaqDTO faq, String searchType, String keyword) {
        if (isBlank(keyword)) return true;
        String key = keyword.trim();

        if ("csCateNo".equals(searchType)) {
            return String.valueOf(faq.getCsCateNo()).equals(key);
        }
        if ("title".equals(searchType)) {
            return contains(faq.getTitle(), key);
        }
        if ("content".equals(searchType)) {
            return contains(faq.getContent(), key);
        }
        return contains(faq.getTitle(), key)
                || contains(faq.getContent(), key)
                || contains(faq.getCateName(), key)
                || contains(faq.getParentCateName(), key);
    }

    private List<QnaDTO> filterQnaList(List<QnaDTO> source, String searchType, String keyword, String status) {
        List<QnaDTO> result = new ArrayList<>();
        for (QnaDTO qna : nullToEmpty(source)) {
            if (matchesQnaStatus(qna, status) && matchesQnaKeyword(qna, searchType, keyword)) {
                result.add(qna);
            }
        }
        return result;
    }

    private boolean matchesQnaStatus(QnaDTO qna, String status) {
        if (isBlank(status)) return true;
        String qnaStatus = isBlank(qna.getStatus()) ? "검토중" : qna.getStatus();
        return qnaStatus.equals(status.trim());
    }

    private boolean matchesQnaKeyword(QnaDTO qna, String searchType, String keyword) {
        if (isBlank(keyword)) return true;
        String key = keyword.trim();

        if ("csCateNo".equals(searchType)) {
            return String.valueOf(qna.getCsCateNo()).equals(key);
        }
        if ("title".equals(searchType)) {
            return contains(qna.getTitle(), key);
        }
        if ("content".equals(searchType)) {
            return contains(qna.getContent(), key);
        }
        if ("name".equals(searchType)) {
            return contains(qna.getName(), key);
        }
        return contains(qna.getTitle(), key)
                || contains(qna.getContent(), key)
                || contains(qna.getName(), key)
                || contains(qna.getCateName(), key)
                || contains(qna.getParentCateName(), key);
    }

    private <T> List<T> getPageList(List<T> source, int currentPage, int pageSize) {
        List<T> list = nullToEmpty(source);
        int fromIndex = Math.min((currentPage - 1) * pageSize, list.size());
        int toIndex = Math.min(fromIndex + pageSize, list.size());
        return list.subList(fromIndex, toIndex);
    }

    private <T> List<T> nullToEmpty(List<T> list) {
        return list == null ? new ArrayList<>() : list;
    }

    private boolean contains(String source, String keyword) {
        if (source == null || keyword == null) return false;
        return source.toLowerCase().contains(keyword.toLowerCase());
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
