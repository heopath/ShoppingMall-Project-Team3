package kr.co.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class IndexController {

    @GetMapping(value = {"/", "/index"})
    public String index(Authentication authentication){

        // authentication 객체 여부로 로그인 여부 확인
        if(authentication != null && authentication.isAuthenticated()){
            return "redirect:/article/list";
        }else{
            return "redirect:/user/login";
        }
    }
}