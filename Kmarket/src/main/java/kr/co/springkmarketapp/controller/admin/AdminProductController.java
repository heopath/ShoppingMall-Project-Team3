package kr.co.springkmarketapp.controller.admin;

import kr.co.springkmarketapp.config.LoginUser;
import kr.co.springkmarketapp.dto.member.SellerProfileDTO;
import kr.co.springkmarketapp.dto.product.ProductDTO;
import kr.co.springkmarketapp.service.member.SellerProfileService;
import kr.co.springkmarketapp.service.product.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminProductController {

    private final ProductService productService;
    private final SellerProfileService sellerProfileService;

    public AdminProductController(ProductService productService,
                                  SellerProfileService sellerProfileService) {
        this.productService = productService;
        this.sellerProfileService = sellerProfileService;
    }

    @GetMapping("/admin/product/list")
    public String list(Model model,
                       @RequestParam(value = "page", defaultValue = "1") int page,
                       @RequestParam(value = "searchType", required = false) String searchType,
                       @RequestParam(value = "keyword", required = false) String keyword,
                       @AuthenticationPrincipal LoginUser userDetails) {

        boolean sellerUser = isSeller(userDetails);
        SellerProfileDTO seller = sellerUser ? requireSeller(userDetails) : null;
        Integer sellerNo = seller != null ? seller.getSellerNo() : null;
        Map<String, Object> result = productService.getAdminProductList(page, searchType, keyword, sellerNo);

        int totalPages = (int) result.get("totalPages");

        // ===== 5단위 페이징 계산 로직 추가 =====
        int pageSize = 5; // 한 화면에 보여줄 페이지 번호 개수 (1~5, 6~10)
        int currentGroup = (int) Math.ceil((double) page / pageSize); // 현재 페이지가 속한 그룹

        int startPage = (currentGroup - 1) * pageSize + 1;
        int endPage = startPage + pageSize - 1;

        // 끝 페이지가 전체 페이지 수보다 크면 전체 페이지 수로 고정
        if (endPage > totalPages) {
            endPage = totalPages;
        }

        // 0 페이지 이하로 떨어지는 예외 방지
        if (startPage < 1) startPage = 1;

        model.addAttribute("products", result.get("list"));
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        // HTML에서 사용할 변수 등록
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        model.addAttribute("searchType", searchType);
        model.addAttribute("keyword", keyword);
        model.addAttribute("isSeller", sellerUser);
        model.addAttribute("currentSeller", seller);

        return "admin/product/list";
    }

    @GetMapping("/admin/product/detail")
    @ResponseBody
    public Map<String, Object> getProductDetail(@RequestParam("no") int productNo,
                                                @AuthenticationPrincipal LoginUser userDetails) {
        ProductDTO product = requireManageableProduct(productNo, userDetails);
        Map<String, Object> result = new HashMap<>();
        result.put("product", product);
        result.put("images", productService.getAllImages(productNo));
        result.put("optionGroups", productService.getProductOptionGroups(productNo));
        result.put("notice", productService.getProductNotice(productNo));
        return result;
    }

    @GetMapping("/admin/product/view")
    public String view(@RequestParam("no") int productNo, Model model,
                       @AuthenticationPrincipal LoginUser userDetails) {
        model.addAttribute("product", requireManageableProduct(productNo, userDetails));
        model.addAttribute("images", productService.getAllImages(productNo));
        model.addAttribute("optionGroups", productService.getProductOptionGroups(productNo));
        model.addAttribute("notice", productService.getProductNotice(productNo));
        return "admin/product/view";
    }

    @GetMapping("/admin/product/modify")
    public String modifyForm(@RequestParam("no") int productNo, Model model,
                             @AuthenticationPrincipal LoginUser userDetails) {
        model.addAttribute("product", requireManageableProduct(productNo, userDetails));
        model.addAttribute("notice", productService.getProductNotice(productNo));
        model.addAttribute("images", productService.getAllImages(productNo));
        return "admin/product/modify";
    }

    @PostMapping("/admin/product/modify")
    @ResponseBody
    public Map<String, Object> modifyProduct(
            ProductDTO dto,
            @RequestParam(required = false) MultipartFile thumb1,
            @RequestParam(required = false) MultipartFile thumb2,
            @RequestParam(required = false) MultipartFile thumb3,
            @RequestParam(required = false) List<MultipartFile> detailImages,
            @AuthenticationPrincipal LoginUser userDetails) {
        requireManageableProduct(dto.getProductNo(), userDetails);
        try {
            productService.modifyProduct(dto, thumb1, thumb2, thumb3, detailImages);
            return Map.of("status", "success", "message", "수정되었습니다.");
        } catch (Exception e) {
            return Map.of("status", "fail", "message", "수정 중 오류가 발생했습니다.");
        }
    }

    @PostMapping("/admin/product/stop")
    @ResponseBody
    public Map<String, Object> stopProduct(@RequestParam("no") Integer productNo,
                                           @AuthenticationPrincipal LoginUser userDetails) {
        requireManageableProduct(productNo, userDetails);
        productService.stopSaleProduct(productNo);
        return Map.of("status", "success", "message", "판매중지 처리되었습니다.");
    }

    @PostMapping("/admin/product/stop-multi")
    @ResponseBody
    public Map<String, Object> stopProducts(@RequestParam("nos") List<Integer> productNos,
                                            @AuthenticationPrincipal LoginUser userDetails) {
        productNos.forEach(productNo -> requireManageableProduct(productNo, userDetails));
        productService.stopSaleProducts(productNos);
        return Map.of("status", "success", "message", "선택 상품이 판매중지 처리되었습니다.");
    }

    @GetMapping("/admin/product/register")
    public String register(Model model,
                           @AuthenticationPrincipal LoginUser userDetails) {
        boolean sellerUser = isSeller(userDetails);
        SellerProfileDTO seller = sellerUser ? requireSeller(userDetails) : null;
        model.addAttribute("isSeller", sellerUser);
        model.addAttribute("currentSeller", seller);
        if (!sellerUser) {
            List<SellerProfileDTO> sellerList = sellerProfileService.selectSellerProfileList().stream()
                    .filter(item -> !"삭제".equals(item.getStatus()))
                    .toList();
            model.addAttribute("sellerList", sellerList);
        }
        return "admin/product/register";
    }

    @PostMapping("/admin/product/register")
    @ResponseBody
    public Map<String, Object> registerProduct(
            ProductDTO dto,
            @RequestParam(required = false) MultipartFile thumb1,
            @RequestParam(required = false) MultipartFile thumb2,
            @RequestParam(required = false) MultipartFile thumb3,
            @RequestParam(required = false) List<MultipartFile> detailImages,
            @AuthenticationPrincipal LoginUser userDetails) {

        try {
            if (isSeller(userDetails)) {
                dto.setSellerNo(requireSeller(userDetails).getSellerNo());
            } else {
                validateSelectedSeller(dto.getSellerNo());
            }
            productService.registerProduct(dto, thumb1, thumb2, thumb3, detailImages);
            return Map.of("status", "success", "message", "상품이 등록되었습니다.");
        } catch (IllegalArgumentException e) {
            return Map.of("status", "fail", "message", e.getMessage());
        } catch (Exception e) {
            return Map.of("status", "fail", "message", "등록 중 오류가 발생했습니다.");
        }
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

    private ProductDTO requireManageableProduct(Integer productNo, LoginUser userDetails) {
        ProductDTO product = productNo != null ? productService.selectProduct(productNo) : null;
        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "상품을 찾을 수 없습니다.");
        }
        if (isSeller(userDetails)) {
            Integer sellerNo = requireSeller(userDetails).getSellerNo();
            if (!sellerNo.equals(product.getSellerNo())) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "본인 상점의 상품만 관리할 수 있습니다.");
            }
        }
        return product;
    }

    private void validateSelectedSeller(Integer sellerNo) {
        if (sellerNo == null) {
            throw new IllegalArgumentException("판매자를 선택하세요.");
        }
        SellerProfileDTO seller = sellerProfileService.selectSellerProfile(sellerNo);
        if (seller == null || "삭제".equals(seller.getStatus())) {
            throw new IllegalArgumentException("등록 가능한 판매자를 선택하세요.");
        }
    }
}
