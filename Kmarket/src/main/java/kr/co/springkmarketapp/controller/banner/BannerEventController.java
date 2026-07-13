package kr.co.springkmarketapp.controller.banner;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BannerEventController {

    /**
     * 관리자 배너의 링크 URL에 /banner/coke 를 입력하면 연결되는 이벤트 페이지.
     */
    @GetMapping("/banner/coke")
    public String cokeEvent() {
        return "banner/coke";
    }
}
