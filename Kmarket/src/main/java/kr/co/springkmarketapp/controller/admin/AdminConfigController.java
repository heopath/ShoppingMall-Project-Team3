package kr.co.springkmarketapp.controller.admin;

import kr.co.springkmarketapp.dto.admin.AppVersionDTO;
import kr.co.springkmarketapp.dto.product.CategoryDTO;
import kr.co.springkmarketapp.entity.AppVersion;
import kr.co.springkmarketapp.service.admin.AppVersionService;
import kr.co.springkmarketapp.service.product.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/config") // 클래스 단위 공통 루트 주소
@RequiredArgsConstructor
public class AdminConfigController {

    private final AppVersionService appVersionService;
    private final CategoryService categoryService;

    @GetMapping("/basic") // /admin/config/basic
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

    // 버전 관리 목록 화면 이동 및 데이터 전달
    @GetMapping("/version")
    public String version(Model model, @RequestParam(value = "page", defaultValue = "1") int page) {
        // 한 페이지에 5개씩, versionNo 내림차순(최신순) 정렬
        Pageable pageable = PageRequest.of(page - 1, 5, Sort.by("versionNo").descending());
        Page<AppVersion> versionPage = appVersionService.selectVersionPage(pageable);

        model.addAttribute("versions", versionPage.getContent()); // 현재 페이지 데이터 목록
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", versionPage.getTotalPages());
        model.addAttribute("latest", appVersionService.getLatestVersion());

        return "admin/config/version";
    }

    // 화면의 버전 등록 Form 요청을 처리할 PostMapping 추가
    @PostMapping("/version/register")
    public String registerVersion(@ModelAttribute("appVersionForm") AppVersion appVersionForm) {

        appVersionService.insertVersion(appVersionForm);

        return "redirect:/admin/config/version";
    }

    @PostMapping("/version/delete")
    public String deleteVersions(@RequestParam("versionCheckbox") List<Integer> versionNos) {
        if (versionNos != null && !versionNos.isEmpty()) {
            appVersionService.deleteVersions(versionNos);
        }
        return "redirect:/admin/config/version";
    }

    @PostMapping("/version/modify")
    public String modifyVersion(@ModelAttribute("appVersionForm") AppVersion appVersionForm) {
        appVersionService.updateVersion(appVersionForm);
        return "redirect:/admin/config/version";
    }

    // 3. 1차 카테고리 목록 조회 API (상품등록 화면 select box용)
    @GetMapping("/category/main-list")
    @ResponseBody
    public List<CategoryDTO> getMainCategories() {
        return categoryService.selectMainCategories();
    }

    // 4. 특정 1차 카테고리 하위의 2차 카테고리 목록 조회 API
    @GetMapping("/category/sub-list")
    @ResponseBody
    public List<CategoryDTO> getSubCategories(@RequestParam("parentNo") Integer parentNo) {
        return categoryService.selectSubCategoriesByParent(parentNo);
    }
}