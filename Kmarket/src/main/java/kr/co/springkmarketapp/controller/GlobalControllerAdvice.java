package kr.co.springkmarketapp.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {

    // application.properties의 spring.application.version 값을 읽어옴
    @Value("${spring.application.version}")
    private String appVersion;

    // 모든 Controller의 뷰(HTML)로 'appVersion'이라는 이름을 항상 전달함
    @ModelAttribute("appVersion")
    public String getAppVersion() {
        return appVersion;
    }
}