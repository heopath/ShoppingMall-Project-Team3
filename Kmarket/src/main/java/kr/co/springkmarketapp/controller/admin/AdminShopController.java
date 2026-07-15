package kr.co.springkmarketapp.controller.admin;

import kr.co.springkmarketapp.config.LoginUser;
import kr.co.springkmarketapp.dto.member.SellerProfileDTO;
import kr.co.springkmarketapp.dto.member.SellerSalesDTO;
import kr.co.springkmarketapp.service.member.SellerProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminShopController {

    private final SellerProfileService sellerProfileService;

    @GetMapping("/admin/shop/list")
    public String list(@RequestParam(defaultValue = "1") int page,
                       @RequestParam(required = false) String searchType,
                       @RequestParam(required = false) String keyword,
                       Model model) {

        int pageSize = 10;
        int pageBlock = 5;

        boolean hasKeyword = keyword != null && !keyword.trim().isEmpty();
        String trimmedKeyword = hasKeyword ? keyword.trim() : null;

        int totalCount = hasKeyword
                ? sellerProfileService.countSellerProfileList(searchType, trimmedKeyword)
                : sellerProfileService.countSellerProfileList();

        int totalPages = Math.max(1, (int) Math.ceil((double) totalCount / pageSize));
        int currentPage = Math.min(Math.max(page, 1), totalPages);
        int offset = (currentPage - 1) * pageSize;

        List<SellerProfileDTO> sellerList = hasKeyword
                ? sellerProfileService.selectSellerProfileList(searchType, trimmedKeyword, offset, pageSize)
                : sellerProfileService.selectSellerProfileList(offset, pageSize);

        int startPage = ((currentPage - 1) / pageBlock) * pageBlock + 1;
        int endPage = Math.min(startPage + pageBlock - 1, totalPages);

        model.addAttribute("sellerList", sellerList);
        model.addAttribute("searchType", searchType);
        model.addAttribute("keyword", keyword);

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("hasPrevPage", currentPage > 1);
        model.addAttribute("hasNextPage", currentPage < totalPages);
        model.addAttribute("totalCount", totalCount);

        return "admin/shop/list";
    }

    @GetMapping("/admin/shop/sales")
    public String sales(@RequestParam(defaultValue = "1") int page,
                        @RequestParam(required = false, defaultValue = "day") String salesPeriod,
                        @AuthenticationPrincipal LoginUser userDetails,
                        Model model) {

        int pageSize = 10;
        int pageBlock = 5;

        String checkedPeriod = normalizeSalesPeriod(salesPeriod);
        boolean sellerUser = isSeller(userDetails);
        SellerProfileDTO seller = sellerUser ? requireSeller(userDetails) : null;
        Integer sellerNo = seller != null ? seller.getSellerNo() : null;

        int totalCount = sellerProfileService.countSellerSalesList(checkedPeriod, sellerNo);
        int totalPages = Math.max(1, (int) Math.ceil((double) totalCount / pageSize));
        int currentPage = Math.min(Math.max(page, 1), totalPages);
        int offset = (currentPage - 1) * pageSize;

        List<SellerSalesDTO> salesList = sellerProfileService.selectSellerSalesList(
                checkedPeriod, offset, pageSize, sellerNo
        );

        int startPage = ((currentPage - 1) / pageBlock) * pageBlock + 1;
        int endPage = Math.min(startPage + pageBlock - 1, totalPages);

        model.addAttribute("salesList", salesList);
        model.addAttribute("salesPeriod", checkedPeriod);
        model.addAttribute("isSeller", sellerUser);
        model.addAttribute("currentSeller", seller);

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("hasPrevPage", currentPage > 1);
        model.addAttribute("hasNextPage", currentPage < totalPages);
        model.addAttribute("totalCount", totalCount);

        return "admin/shop/sales";
    }

    private boolean isSeller(LoginUser userDetails) {
        return userDetails != null
                && userDetails.getMember() != null
                && "SELLER".equalsIgnoreCase(userDetails.getMember().getRole());
    }

    private SellerProfileDTO requireSeller(LoginUser userDetails) {
        SellerProfileDTO seller = sellerProfileService.selectSellerProfileByMemberNo(
                userDetails.getMember().getMemberNo()
        );
        if (seller == null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "판매자 프로필을 찾을 수 없습니다.");
        }
        return seller;
    }

    @PostMapping("/admin/shop/status")
    public String updateStatus(@RequestParam Integer sellerNo,
                               @RequestParam String status,
                               @RequestParam(required = false, defaultValue = "1") int page,
                               @RequestParam(required = false) String searchType,
                               @RequestParam(required = false) String keyword) {

        sellerProfileService.updateSellerStatus(sellerNo, status);

        String redirectUrl = UriComponentsBuilder
                .fromPath("/admin/shop/list")
                .queryParam("page", page)
                .queryParam("searchType", searchType)
                .queryParam("keyword", keyword)
                .build()
                .encode()
                .toUriString();

        return "redirect:" + redirectUrl;
    }

    @PostMapping("/admin/shop/delete")
    public String deleteShop(@RequestParam(required = false) List<Integer> sellerNos,
                             @RequestParam(required = false, defaultValue = "1") int page,
                             @RequestParam(required = false) String searchType,
                             @RequestParam(required = false) String keyword,
                             RedirectAttributes redirectAttributes) {

        if (sellerNos == null || sellerNos.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "삭제할 상점을 선택하세요.");

            String redirectUrl = buildShopListRedirectUrl(page, searchType, keyword);
            return "redirect:" + redirectUrl;
        }

        sellerProfileService.deleteSellerProfiles(sellerNos);
        redirectAttributes.addFlashAttribute("message", "선택한 상점이 삭제되었습니다.");

        String redirectUrl = buildShopListRedirectUrl(page, searchType, keyword);
        return "redirect:" + redirectUrl;
    }

    @PostMapping("/admin/shop/register")
    public String registerShop(SellerProfileDTO sellerProfileDTO,
                               RedirectAttributes redirectAttributes) {

        try {
            sellerProfileService.insertSellerProfile(sellerProfileDTO);
            redirectAttributes.addFlashAttribute("message", "상점이 등록되었습니다.");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }

        return "redirect:/admin/shop/list";
    }

    private String buildShopListRedirectUrl(int page, String searchType, String keyword) {
        return UriComponentsBuilder
                .fromPath("/admin/shop/list")
                .queryParam("page", page)
                .queryParam("searchType", searchType)
                .queryParam("keyword", keyword)
                .build()
                .encode()
                .toUriString();
    }

    private String normalizeSalesPeriod(String salesPeriod) {
        if ("week".equals(salesPeriod) || "month".equals(salesPeriod)) {
            return salesPeriod;
        }
        return "day";
    }
}
