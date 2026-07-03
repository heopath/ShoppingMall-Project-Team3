package kr.co.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.co.sboard.dto.TermsDTO;
import kr.co.sboard.dto.UserCheckDTO;
import kr.co.sboard.dto.UserDTO;
import kr.co.sboard.service.EmailService;
import kr.co.sboard.service.TermsService;
import kr.co.sboard.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Log4j2
@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;
    private final TermsService termsService;
    private final EmailService emailService;

    @GetMapping("/user/info")
    public String info(){
        // ⭕ 맨 앞 슬래시 제거
        return "user/info";
    }

    @GetMapping("/user/login")
    public String login(){
        // ⭕ 맨 앞 슬래시 제거 완료 상태
        return "user/login";
    }

    @GetMapping("/user/register")
    public String register(){
        // ⭕ 맨 앞 슬래시 제거
        return "user/register";
    }

    @PostMapping("/user/register")
    public String register(UserDTO userDTO, HttpServletRequest req){
        // 클라이언트 IP
        String regip = req.getRemoteAddr();
        userDTO.setRegip(regip);

        log.info(userDTO);

        // 등록 서비스 호출
        userService.register(userDTO);

        // 💡 redirect: 뒤의 주소 체계는 URL 경로이므로 슬래시를 유지하는 것이 맞습니다.
        return "redirect:/user/login?register=success";
    }

    @GetMapping("/user/terms")
    public String terms(Model model){
        // 약관 조회 서비스 호출
        TermsDTO termsDTO = termsService.get(1);
        log.info(termsDTO);

        // 모델 참조
        model.addAttribute(termsDTO);

        // ⭕ 맨 앞 슬래시 제거
        return "user/terms";
    }

    @ResponseBody
    @GetMapping("/user/check")
    public ResponseEntity<Map<String, Integer>> check(UserCheckDTO dto, HttpSession session){
        log.info(dto);

        // 서비스 호출
        int count = userService.getCount(dto);

        if(dto.getType().equals("email") && count == 0){
            int code = emailService.sendCode(dto.getValue());
            System.out.println("============== 발송된 인증코드: " + code + " ==============");
            session.setAttribute("sessCode", String.valueOf(code));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Map.of("count", count));
    }

    @ResponseBody
    @PostMapping("/user/check")
    public ResponseEntity<Map<String, Integer>> check(@RequestBody Map<String, String> jsonData, HttpSession session){
        log.info(jsonData.get("code"));

        // 세션에 저장된 코드와 클라이트가 전송한 코드가 일치하는 여부
        String sessCode = (String) session.getAttribute("sessCode");
        String jsonCode = jsonData.get("code");

        if(sessCode.equals(jsonCode)){

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(Map.of("count", 0));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Map.of("count", 1));
    }
}