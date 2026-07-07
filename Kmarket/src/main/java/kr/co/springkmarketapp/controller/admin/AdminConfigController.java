package kr.co.springkmarketapp.controller.admin;

import kr.co.springkmarketapp.dto.product.CategoryDTO;
import kr.co.springkmarketapp.entity.Category;
import kr.co.springkmarketapp.service.product.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin/config")
@RequiredArgsConstructor
public class AdminConfigController {

    private final CategoryService categoryService;

    @GetMapping("/basic")
    public String basic() {
        return "admin/config/basic";
    }

    @GetMapping("/banner")
    public String banner() {
        return "admin/config/banner";
    }

    @GetMapping("/policy")
    public String policy() {
        return "admin/config/policy";
    }

    @GetMapping("/category")
    public String category(Model model) {
        model.addAttribute("categoryTree", categoryService.getCategoryTree());
        return "admin/config/category";
    }

    // 1. 카테고리 등록 API (1차, 2차 공용)
    @PostMapping("/category/register")
    @ResponseBody // JSON 데이터를 브라우저로 직접 리턴하기 위해 선언
    public Map<String, Object> registerCategory(CategoryDTO categoryDTO) {
        Map<String, Object> resultMap = new HashMap<>();

        // depth 분기 처리
        if (categoryDTO.getParentNo() == null) {
            categoryDTO.setDepth(1); // 부모 번호가 없으면 1차 대분류
        } else {
            categoryDTO.setDepth(2); // 부모 번호가 있으면 2차 중분류
        }
        categoryDTO.setUseYn("Y");
        categoryDTO.setSortOrder(1); // 기본 정렬값 지정

        // 서비스 호출하여 DB INSERT 실행
        int result = categoryService.insertCategory(categoryDTO);

        if (result > 0) {
            resultMap.put("status", "success");
            resultMap.put("message", "카테고리가 성공적으로 등록되었습니다.");
        } else {
            resultMap.put("status", "fail");
            resultMap.put("message", "등록에 실패했습니다.");
        }
        return resultMap;
    }

    // 2. 카테고리 삭제 API
    @PostMapping("/category/delete")
    @ResponseBody
    public Map<String, Object> deleteCategory(@RequestParam("cateNo") Integer cateNo) {
        Map<String, Object> resultMap = new HashMap<>();

        // 서비스 호출하여 DB DELETE 실행
        int result = categoryService.deleteCategory(cateNo);

        if (result > 0) {
            resultMap.put("status", "success");
            resultMap.put("message", "카테고리가 성공적으로 삭제되었습니다.");
        } else {
            resultMap.put("status", "fail");
            resultMap.put("message", "삭제에 실패했습니다. 해당 카테고리를 참조하는 상품이 있는지 확인하세요.");
        }
        return resultMap;
    }

    @GetMapping("/version")
    public String version() {
        return "admin/config/version";
    }
}
