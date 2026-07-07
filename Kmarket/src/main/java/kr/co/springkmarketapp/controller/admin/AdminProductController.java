package kr.co.springkmarketapp.controller.admin;

import jakarta.servlet.http.HttpSession;
import kr.co.springkmarketapp.dto.product.ProductDTO;
import kr.co.springkmarketapp.service.product.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Controller
public class AdminProductController {

    private final ProductService productService;

    public AdminProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/admin/product/list")
    public String list() {
        return "admin/product/list";
    }

    @GetMapping("/admin/product/register")
    public String register() {
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
            HttpSession session) {

        Integer sellerNo = (Integer) session.getAttribute("sellerNo");
        dto.setSellerNo(sellerNo != null ? sellerNo : 1);  // 테스트용 임시값 1 * 로그인 기능 구현 후 수정필요

        try {
            productService.registerProduct(dto, thumb1, thumb2, thumb3, detailImages);
            return Map.of("status", "success", "message", "상품이 등록되었습니다.");
        } catch (Exception e) {
            return Map.of("status", "fail", "message", "등록 중 오류가 발생했습니다.");
        }
    }
}
