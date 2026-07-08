package kr.co.springkmarketapp.controller.admin;

import kr.co.springkmarketapp.dto.coupon.CouponDTO;
import kr.co.springkmarketapp.dto.coupon.CouponIssueDTO;
import kr.co.springkmarketapp.service.coupon.CouponIssueService;
import kr.co.springkmarketapp.service.coupon.CouponService;
import kr.co.springkmarketapp.service.member.MemberService;
import kr.co.springkmarketapp.service.member.SellerProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminCouponController {

    private final CouponService couponService;
    private final CouponIssueService couponIssueService;
    private final SellerProfileService sellerProfileService;
    private final MemberService memberService;

    @GetMapping("/admin/coupon/list")
    public String list(@RequestParam(defaultValue = "1") int page,
                       @RequestParam(required = false) String searchType,
                       @RequestParam(required = false) String keyword,
                       Model model) {

        int pageSize = 10;
        int pageBlock = 5;

        boolean hasKeyword = keyword != null && !keyword.trim().isEmpty();
        String trimmedKeyword = hasKeyword ? keyword.trim() : null;

        int totalCount = hasKeyword
                ? couponService.countCouponList(searchType, trimmedKeyword)
                : couponService.countCouponList();

        int totalPages = Math.max(1, (int) Math.ceil((double) totalCount / pageSize));
        int currentPage = Math.min(Math.max(page, 1), totalPages);
        int offset = (currentPage - 1) * pageSize;

        List<CouponDTO> couponList = hasKeyword
                ? couponService.selectCouponList(searchType, trimmedKeyword, offset, pageSize)
                : couponService.selectCouponList(offset, pageSize);

        int startPage = ((currentPage - 1) / pageBlock) * pageBlock + 1;
        int endPage = Math.min(startPage + pageBlock - 1, totalPages);

        model.addAttribute("couponList", couponList);
        model.addAttribute("sellerList", sellerProfileService.selectSellerProfileList());
        model.addAttribute("searchType", searchType);
        model.addAttribute("keyword", keyword);
        addPagination(model, currentPage, totalPages, startPage, endPage, totalCount);

        return "admin/coupon/list";
    }

    @PostMapping("/admin/coupon/register")
    public String register(CouponDTO couponDTO,
                           RedirectAttributes redirectAttributes) {

        // 최종 DB 기준:
        // seller_no는 NULL 허용이므로 필수값으로 막지 않는다.
        // benefit_value, min_order_price, max_discount_price는 NOT NULL이므로 빈 값이면 0으로 보정한다.
        if (couponDTO.getCouponCode() == null || couponDTO.getCouponCode().isBlank()) {
            couponDTO.setCouponCode("CP" + System.currentTimeMillis());
        }

        if (couponDTO.getCouponType() == null || couponDTO.getCouponType().isBlank()) {
            redirectAttributes.addFlashAttribute("errorMessage", "쿠폰종류를 선택하세요.");
            return "redirect:/admin/coupon/list";
        }

        if (couponDTO.getCouponName() == null || couponDTO.getCouponName().isBlank()) {
            redirectAttributes.addFlashAttribute("errorMessage", "쿠폰명을 입력하세요.");
            return "redirect:/admin/coupon/list";
        }

        if (couponDTO.getBenefitType() == null || couponDTO.getBenefitType().isBlank()) {
            redirectAttributes.addFlashAttribute("errorMessage", "혜택을 선택하세요.");
            return "redirect:/admin/coupon/list";
        }

        if (couponDTO.getBenefitValue() == null) {
            couponDTO.setBenefitValue(0);
        }

        if (couponDTO.getMinOrderPrice() == null) {
            couponDTO.setMinOrderPrice(0);
        }

        if (couponDTO.getMaxDiscountPrice() == null) {
            couponDTO.setMaxDiscountPrice(0);
        }

        if (couponDTO.getStatus() == null || couponDTO.getStatus().isBlank()) {
            couponDTO.setStatus("사용");
        }

        try {
            couponService.insertCoupon(couponDTO);
            redirectAttributes.addFlashAttribute("message", "쿠폰이 등록되었습니다.");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "쿠폰 등록 중 오류가 발생했습니다. 입력값과 DB 컬럼을 확인하세요.");
        }

        return "redirect:/admin/coupon/list";
    }

    @PostMapping("/admin/coupon/status")
    public String updateStatus(@RequestParam Integer couponNo,
                               @RequestParam String status,
                               @RequestParam(required = false, defaultValue = "1") int page,
                               @RequestParam(required = false) String searchType,
                               @RequestParam(required = false) String keyword) {
        couponService.updateCouponStatus(couponNo, status);
        return "redirect:" + buildCouponListRedirectUrl(page, searchType, keyword);
    }

    @PostMapping("/admin/coupon/delete")
    public String delete(@RequestParam(required = false) List<Integer> couponNos,
                         @RequestParam(required = false, defaultValue = "1") int page,
                         @RequestParam(required = false) String searchType,
                         @RequestParam(required = false) String keyword,
                         RedirectAttributes redirectAttributes) {

        if (couponNos == null || couponNos.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "삭제할 쿠폰을 선택하세요.");
            return "redirect:" + buildCouponListRedirectUrl(page, searchType, keyword);
        }

        couponService.deleteCoupons(couponNos);
        redirectAttributes.addFlashAttribute("message", "선택한 쿠폰이 삭제되었습니다.");
        return "redirect:" + buildCouponListRedirectUrl(page, searchType, keyword);
    }

    @GetMapping("/admin/coupon/issued")
    public String issued(@RequestParam(defaultValue = "1") int page,
                         @RequestParam(required = false) String searchType,
                         @RequestParam(required = false) String keyword,
                         Model model) {

        int pageSize = 10;
        int pageBlock = 5;

        boolean hasKeyword = keyword != null && !keyword.trim().isEmpty();
        String trimmedKeyword = hasKeyword ? keyword.trim() : null;

        int totalCount = hasKeyword
                ? couponIssueService.countCouponIssueList(searchType, trimmedKeyword)
                : couponIssueService.countCouponIssueList();

        int totalPages = Math.max(1, (int) Math.ceil((double) totalCount / pageSize));
        int currentPage = Math.min(Math.max(page, 1), totalPages);
        int offset = (currentPage - 1) * pageSize;

        List<CouponIssueDTO> issueList = hasKeyword
                ? couponIssueService.selectCouponIssueList(searchType, trimmedKeyword, offset, pageSize)
                : couponIssueService.selectCouponIssueList(offset, pageSize);

        int startPage = ((currentPage - 1) / pageBlock) * pageBlock + 1;
        int endPage = Math.min(startPage + pageBlock - 1, totalPages);

        model.addAttribute("issueList", issueList);
        model.addAttribute("couponList", couponService.selectCouponList());
        model.addAttribute("memberList", memberService.selectMemberList());
        model.addAttribute("searchType", searchType);
        model.addAttribute("keyword", keyword);
        addPagination(model, currentPage, totalPages, startPage, endPage, totalCount);

        return "admin/coupon/issued";
    }

    @PostMapping("/admin/coupon/issue/register")
    public String issueRegister(CouponIssueDTO issueDTO,
                                RedirectAttributes redirectAttributes) {
        if (issueDTO.getIssueCode() == null || issueDTO.getIssueCode().isBlank()) {
            issueDTO.setIssueCode("ISS" + System.currentTimeMillis());
        }

        if (issueDTO.getStatus() == null || issueDTO.getStatus().isBlank()) {
            issueDTO.setStatus("발급");
        }

        if (issueDTO.getCouponNo() == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "발급할 쿠폰을 선택하세요.");
            return "redirect:/admin/coupon/issued";
        }

        if (issueDTO.getMemberNo() == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "발급 대상 회원을 선택하세요.");
            return "redirect:/admin/coupon/issued";
        }

        try {
            couponIssueService.insertCouponIssue(issueDTO);
            redirectAttributes.addFlashAttribute("message", "쿠폰이 발급되었습니다.");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "쿠폰 발급 중 오류가 발생했습니다. 쿠폰번호와 회원번호를 확인하세요.");
        }

        return "redirect:/admin/coupon/issued";
    }

    @PostMapping("/admin/coupon/issue/status")
    public String issueStatus(@RequestParam Long issueNo,
                              @RequestParam String status,
                              @RequestParam(required = false, defaultValue = "1") int page,
                              @RequestParam(required = false) String searchType,
                              @RequestParam(required = false) String keyword) {
        couponIssueService.updateCouponIssueStatus(issueNo, status);
        return "redirect:" + buildIssuedRedirectUrl(page, searchType, keyword);
    }

    @PostMapping("/admin/coupon/issue/delete")
    public String issueDelete(@RequestParam Long issueNo,
                              @RequestParam(required = false, defaultValue = "1") int page,
                              @RequestParam(required = false) String searchType,
                              @RequestParam(required = false) String keyword,
                              RedirectAttributes redirectAttributes) {
        couponIssueService.deleteCouponIssue(issueNo);
        redirectAttributes.addFlashAttribute("message", "발급 쿠폰이 삭제되었습니다.");
        return "redirect:" + buildIssuedRedirectUrl(page, searchType, keyword);
    }

    private void addPagination(Model model, int currentPage, int totalPages, int startPage, int endPage, int totalCount) {
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("hasPrevPage", currentPage > 1);
        model.addAttribute("hasNextPage", currentPage < totalPages);
        model.addAttribute("totalCount", totalCount);
    }

    private String buildCouponListRedirectUrl(int page, String searchType, String keyword) {
        return UriComponentsBuilder
                .fromPath("/admin/coupon/list")
                .queryParam("page", page)
                .queryParam("searchType", searchType)
                .queryParam("keyword", keyword)
                .build()
                .encode()
                .toUriString();
    }

    private String buildIssuedRedirectUrl(int page, String searchType, String keyword) {
        return UriComponentsBuilder
                .fromPath("/admin/coupon/issued")
                .queryParam("page", page)
                .queryParam("searchType", searchType)
                .queryParam("keyword", keyword)
                .build()
                .encode()
                .toUriString();
    }
}
