package kr.co.springkmarketapp.controller.product;

import kr.co.springkmarketapp.dto.common.PageRequestDTO;
import kr.co.springkmarketapp.dto.common.PageResponseDTO;
import kr.co.springkmarketapp.dto.product.CategoryDTO;
import kr.co.springkmarketapp.dto.product.ProductListDTO;
import kr.co.springkmarketapp.dto.product.ProductViewDTO;
import kr.co.springkmarketapp.service.admin.BannerService;
import kr.co.springkmarketapp.service.member.SellerProfileService;
import kr.co.springkmarketapp.service.product.CategoryService;
import kr.co.springkmarketapp.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final BannerService bannerService;
    private final SellerProfileService sellerProfileService;

    // aside 및 광고 부분 공통으로 메서드 처리
    private void addCommonModel(Model model) {
        model.addAttribute("topBanner", bannerService.getTopBanner());
        model.addAttribute("categoryTree", categoryService.getCategoryTree());
        model.addAttribute("bestProducts", productService.getBestProducts());
    }

    private CategoryDTO addCategoryModel(int cateNo, Model model) {

        CategoryDTO currentCategory = categoryService.getCategory(cateNo);

        if (currentCategory == null) {
            return null;
        }

        CategoryDTO parentCategory = null;

        if (currentCategory.getParentNo() != null) {
            parentCategory =
                    categoryService.getCategory(currentCategory.getParentNo());
        }

        model.addAttribute("currentCategory", currentCategory);
        model.addAttribute("parentCategory", parentCategory);

        return currentCategory;
    }

    @GetMapping("/product/list")
    public String list(PageRequestDTO pageRequestDTO, Model model) {
        CategoryDTO currentCategory = addCategoryModel(pageRequestDTO.getCateNo(), model);

        if (currentCategory == null) {
            return "redirect:/";
        }

        addCommonModel(model);

        PageResponseDTO<ProductListDTO> pageResponseDTO = productService.getProductsByCategory(pageRequestDTO);

        model.addAttribute("pageResponseDTO", pageResponseDTO);

        return "product/list";
    }

    @GetMapping("/product/view")
    public String view( @RequestParam("productNo") int productNo,
                        @RequestParam(value = "reviewPage", defaultValue = "1") int reviewPage,
                        Model model) {

        // header + aside 공통 데이터
        addCommonModel(model);

        // 상품 상세 데이터
        ProductViewDTO product = productService.getProductView(productNo);

        CategoryDTO currentCategory = addCategoryModel(product.getCateNo(), model);

        if (currentCategory == null) {
            return "redirect:/";
        }

        PageRequestDTO reviewPageRequestDTO = new PageRequestDTO();
        reviewPageRequestDTO.setPage(Math.max(reviewPage, 1));
        reviewPageRequestDTO.setSize(5); // 현재 화면에 리뷰 5개씩 출력

        model.addAttribute("product", product);
        model.addAttribute("optionGroups", productService.getProductOptionGroups(productNo));
        model.addAttribute("seller", sellerProfileService.selectSellerProfile(product.getSellerNo()));
        model.addAttribute("detailImages", productService.getDetailImages(productNo));
        model.addAttribute("notice", productService.getProductNotice(productNo));
        model.addAttribute("reviewPageResponseDTO", productService.getProductReviews(productNo, reviewPageRequestDTO));

        return "product/view";
    }

    @GetMapping("/product/cart")
    public String cart() {
        return "product/cart";
    }

    @GetMapping("/product/order")
    public String order() {
        return "product/order";
    }

    @GetMapping("/product/complete")
    public String complete() {
        return "product/complete";
    }

    @GetMapping("/product/search")
    public String search() {
        return "product/search";
    }
}
