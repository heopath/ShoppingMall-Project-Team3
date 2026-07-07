package kr.co.springkmarketapp.controller.member.find;

import jakarta.servlet.http.HttpSession;
import kr.co.springkmarketapp.dto.member.MemberDTO;
import kr.co.springkmarketapp.service.member.EmailService;
import kr.co.springkmarketapp.service.member.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class MemberFindController {
    private final MemberService memberService;
    private final EmailService emailService;

    public MemberFindController(MemberService memberService, EmailService emailService) {
        this.memberService = memberService;
        this.emailService = emailService;
    }

    @GetMapping("/member/find/userId")
    public String findUserId() {
        return "member/find/userId";
    }

    @GetMapping("/member/find/password")
    public String findPassword() {
        return "member/find/password";
    }

    // 아이디 결과
    @GetMapping("/member/find/resultId")
    public String resultId(String memberId, Model model) {

        MemberDTO member = memberService.selectMemberByMemberId(memberId);

        model.addAttribute("member", member);

        return "member/find/resultId";
    }

    // 비밀번호 변경
    @GetMapping("/member/find/changePassword")
    public String changePassword(String memberId, Model model) {

        model.addAttribute("memberId", memberId);

        return "member/find/changePassword";
    }

    // ============= 아이디/ 비번찾기 API =============
    // 이름 + 이메일 존재 여부 확인
    @ResponseBody
    @GetMapping("/member/find/checkEmail")
    public ResponseEntity<Map<String, Integer>> checkEmail(String name, String email) {

        int count = memberService.countByNameAndEmail(name, email);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Map.of("count", count));
    }

    // 인증코드 발송
    @ResponseBody
    @PostMapping("/member/find/sendCode")
    public ResponseEntity<Map<String, Integer>> sendCode(String email, HttpSession session) {

        String code = emailService.sendCode(email);

        session.setAttribute("findCode", code);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Map.of("result", 1));
    }

    // 인증코드 확인
    @ResponseBody
    @PostMapping("/member/find/verifyCode")
    public ResponseEntity<Map<String, Integer>> verifyCode(
            @RequestBody Map<String, String> jsonData,
            HttpSession session) {

        String inputCode = jsonData.get("code");
        String sessionCode = (String) session.getAttribute("findCode");

        if (sessionCode != null && sessionCode.equals(inputCode)) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(Map.of("count", 0));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Map.of("count", 1));
    }

    // 아이디 찾기
    @ResponseBody
    @PostMapping("/member/find/findId")
    public ResponseEntity<Map<String, String>> findId(String name, String email) {

        String memberId = memberService
                .findMemberId(name, email);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Map.of("memberId", memberId));
    }

    // 아이디 + 이메일 여부 확인(비밀번호 찾기)
    @ResponseBody
    @GetMapping("/member/find/checkMember")
    public ResponseEntity<Map<String, Integer>> checkMember(String memberId, String email) {

        int count = memberService.countByMemberIdAndEmail(memberId, email);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Map.of("count", count));
    }

    // 비밀번호 변경
    @ResponseBody
    @PostMapping("/member/find/changePassword")
    public ResponseEntity<Map<String, Integer>> changePassword(String memberId,
                                                               String password) {

        int result = memberService.changePassword(memberId, password);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Map.of("result", result));
    }


}
