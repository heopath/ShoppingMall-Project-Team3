package kr.co.controller.cs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CsMainController {

    @GetMapping({"/cs", "/cs/index"})
    public String index() {
        return "cs/index";
    }
}
