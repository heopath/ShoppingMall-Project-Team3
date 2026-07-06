package kr.co.springkmarketapp.controller.admin;

import kr.co.springkmarketapp.dto.member.SellerProfileDTO;
import kr.co.springkmarketapp.service.member.SellerProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AdminShopController {

    private final SellerProfileService sellerProfileService;

    @GetMapping("/admin/shop/list")
    public String list(Model model) {
        model.addAttribute("sellerList", sellerProfileService.selectSellerProfileList());
        return "admin/shop/list";
    }

    @GetMapping("/admin/shop/sales")
    public String sales(Model model) {
        model.addAttribute("sellerList", sellerProfileService.selectSellerProfileList());
        return "admin/shop/sales";
    }

    @PostMapping("/admin/shop/status")
    public String updateStatus(@RequestParam Integer sellerNo, @RequestParam String status) {
        SellerProfileDTO seller = sellerProfileService.selectSellerProfile(sellerNo);
        if (seller != null) {
            seller.setStatus(status);
            sellerProfileService.updateSellerProfile(seller);
        }
        return "redirect:/admin/shop/list";
    }

    @PostMapping("/admin/shop/delete")
    public String delete(@RequestParam Integer sellerNo) {
        sellerProfileService.deleteSellerProfile(sellerNo);
        return "redirect:/admin/shop/list";
    }
}
