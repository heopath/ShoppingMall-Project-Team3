package kr.co.springkmarketapp.controller.admin;

import kr.co.springkmarketapp.dto.admin.AppVersionDTO;
import kr.co.springkmarketapp.entity.AppVersion;
import kr.co.springkmarketapp.service.admin.AppVersionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/config") // 클래스 단위 공통 루트 주소
@RequiredArgsConstructor
public class AdminConfigController {

    private final AppVersionService appVersionService;

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
    public String category() {
        return "admin/config/category";
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

}