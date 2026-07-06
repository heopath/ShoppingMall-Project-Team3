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
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AdminCouponController {

    private final CouponService couponService;
    private final CouponIssueService couponIssueService;
    private final SellerProfileService sellerProfileService;
    private final MemberService memberService;

    @GetMapping("/admin/coupon/list")
    public String list(Model model) {
        model.addAttribute("couponList", couponService.selectCouponList());
        model.addAttribute("sellerList", sellerProfileService.selectSellerProfileList());
        return "admin/coupon/list";
    }

    @PostMapping("/admin/coupon/register")
    public String register(CouponDTO couponDTO) {
        if (couponDTO.getCouponCode() == null || couponDTO.getCouponCode().isBlank()) {
            couponDTO.setCouponCode("CP" + System.currentTimeMillis());
        }
        couponService.insertCoupon(couponDTO);
        return "redirect:/admin/coupon/list";
    }

    @PostMapping("/admin/coupon/status")
    public String updateStatus(@RequestParam Integer couponNo, @RequestParam String status) {
        CouponDTO coupon = couponService.selectCoupon(couponNo);
        if (coupon != null) {
            coupon.setStatus(status);
            couponService.updateCoupon(coupon);
        }
        return "redirect:/admin/coupon/list";
    }

    @PostMapping("/admin/coupon/delete")
    public String delete(@RequestParam Integer couponNo) {
        couponService.deleteCoupon(couponNo);
        return "redirect:/admin/coupon/list";
    }

    @GetMapping("/admin/coupon/issued")
    public String issued(Model model) {
        model.addAttribute("issueList", couponIssueService.selectCouponIssueList());
        model.addAttribute("couponList", couponService.selectCouponList());
        model.addAttribute("memberList", memberService.selectMemberList());
        return "admin/coupon/issued";
    }

    @PostMapping("/admin/coupon/issue/register")
    public String issueRegister(CouponIssueDTO issueDTO) {
        if (issueDTO.getIssueCode() == null || issueDTO.getIssueCode().isBlank()) {
            issueDTO.setIssueCode("ISS" + System.currentTimeMillis());
        }
        couponIssueService.insertCouponIssue(issueDTO);
        return "redirect:/admin/coupon/issued";
    }

    @PostMapping("/admin/coupon/issue/delete")
    public String issueDelete(@RequestParam Long issueNo) {
        couponIssueService.deleteCouponIssue(issueNo);
        return "redirect:/admin/coupon/issued";
    }
}
