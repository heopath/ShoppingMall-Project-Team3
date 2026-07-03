package kr.co.springkmarketapp.controller.member.find;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberFindController {
    @GetMapping("/member/find/userId")
    public String findUserId() {
        return "member/find/userId";
    }

    @GetMapping("/member/find/password")
    public String findPassword() {
        return "member/find/password";
    }

    @GetMapping("/member/find/resultId")
    public String findResultId() {
        return "member/find/resultId";
    }

    @GetMapping("/member/find/changePassword")
    public String changePassword() {
        return "member/find/changePassword";
    }
}
