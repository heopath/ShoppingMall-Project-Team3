package kr.co.springkmarketapp.controller;

import kr.co.springkmarketapp.service.admin.SiteSettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Map;

@ControllerAdvice
public class GlobalControllerAdvice {

    private final SiteSettingService siteSettingService;

    // DB에 저장된 버전이 없을 때 사용할 기본 버전
    @Value("${spring.application.version:0.0.0}")
    private String defaultAppVersion;

    public GlobalControllerAdvice(SiteSettingService siteSettingService) {
        this.siteSettingService = siteSettingService;
    }

    // 기본설정에서 저장한 버전을 모든 화면의 푸터에 전달
    @ModelAttribute("appVersion")
    public String getAppVersion() {
        return siteSettingService.getSettingValue(
                "app_version",
                defaultAppVersion
        );
    }

    @ModelAttribute("settings")
    public Map<String, String> getSettings() {
        return siteSettingService.getSettingsMap();
    }
}
