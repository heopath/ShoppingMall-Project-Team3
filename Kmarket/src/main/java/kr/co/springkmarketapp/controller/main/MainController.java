package kr.co.springkmarketapp.controller.main;

import kr.co.springkmarketapp.service.admin.BannerService;
import kr.co.springkmarketapp.service.product.CategoryService;
import kr.co.springkmarketapp.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final BannerService bannerService;


    @GetMapping({"/", "/index"})
    public String index(Model model) {


        // 배너 가져오기
        model.addAttribute("topBanner", bannerService.getTopBanner());
        model.addAttribute("main2Banners", bannerService.getMain2Banners());

        // 카테고리 1차 + 2차 구조
        model.addAttribute("categoryTree", categoryService.getCategoryTree());

        // product 리스트 요청 후 모델 참조
        model.addAttribute("bestProducts", productService.getBestProducts());
        model.addAttribute("hitProducts", productService.getHitProducts());
        model.addAttribute("recommendProducts", productService.getRecommendProducts());
        model.addAttribute("newProducts", productService.getNewProducts());
        model.addAttribute("popularProducts", productService.getPopularProducts());
        model.addAttribute("discountProducts", productService.getDiscountProducts());

        return "index";
    }
}
