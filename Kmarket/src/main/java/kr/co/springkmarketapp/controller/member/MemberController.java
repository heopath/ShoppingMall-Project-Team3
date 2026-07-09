package kr.co.springkmarketapp.controller.member;

import jakarta.servlet.http.HttpSession;
import kr.co.springkmarketapp.dto.member.MemberCheckDTO;
import kr.co.springkmarketapp.dto.member.MemberDTO;
import kr.co.springkmarketapp.dto.member.SellerProfileDTO;
import kr.co.springkmarketapp.entity.member.Member;
import kr.co.springkmarketapp.service.member.EmailService;
import kr.co.springkmarketapp.service.member.MemberService;
import kr.co.springkmarketapp.service.member.SellerProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import kr.co.springkmarketapp.service.policy.PolicyService;

import java.util.Map;

@Log4j2
@RequiredArgsConstructor
@Controller
public class MemberController {
    
    private final MemberService memberService;
    private final EmailService emailService;
    private final SellerProfileService sellerProfileService;

    //약관 정책
    private final PolicyService policyService;

    @GetMapping("/member/login")
    public String login() {
        return "member/login";
    }

    @GetMapping("/member/join")
    public String join() {
        return "member/join";
    }

    @GetMapping("/member/register")
    public String register() {
        return "member/register";
    }

    @PostMapping("/member/register")
    public String register(MemberDTO memberDTO) {
        memberService.insertMember(memberDTO);
        return "redirect:/member/login?register=success";
    }

    @GetMapping("/member/registerSeller")
    public String registerSeller() {
        return "member/registerSeller";
    }

    @PostMapping("/member/registerSeller")
    public String registerSeller(MemberDTO memberDTO,
                                 SellerProfileDTO sellerProfileDTO) {
        // 대표자명과 전화번호를 memeberDTO의 name과 hp에 저장
        memberDTO.setName(sellerProfileDTO.getCeoName());
        memberDTO.setHp(sellerProfileDTO.getTel());

        // 판매자 권한 설정
        memberDTO.setRole("SELLER");
        // member 저장
        Member member = memberService.insertMember(memberDTO);

        // FK 설정
        sellerProfileDTO.setMemberNo(member.getMemberNo());

        // seller_profile 저장
        sellerProfileService.insertSellerProfile(sellerProfileDTO);

        return "redirect:/member/login?register=success";
    }

    @GetMapping("/member/signup")
    public String signup(@RequestParam String type, Model model) {
        model.addAttribute("type", type);
        model.addAttribute("policies", policyService.selectSignupPolicyList(type));
        return "member/signup";
    }

    // 중복 확인
    @ResponseBody
    @GetMapping("/user/check")
    public ResponseEntity<Map<String, Integer>> check(MemberCheckDTO dto, HttpSession session){
        log.info(dto);

        // 서비스 호출
        int count = memberService.getCount(dto);

        if(dto.getType().equals("email") && count == 0){
            String code = emailService.sendCode(dto.getValue());
            session.setAttribute("sessCode", code);
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Map.of("count", count));
    }

    // 이메일 인증번호 확인
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

    // 사업자등록번호 중복 확인
    @GetMapping("/member/checkBizNo")
    @ResponseBody
    public int checkBizNo(String bizNo) {
        return sellerProfileService.countBizNo(bizNo);
    }

    // 상호명 중복 확인
    @GetMapping("/member/checkCompanyName")
    @ResponseBody
    public int checkCompanyName(String companyName) {
        return sellerProfileService.countCompanyName(companyName);
    }

    // 통신판매업번호 중복 확인
    @GetMapping("/member/checkOnlineSaleNo")
    @ResponseBody
    public int checkOnlineSaleNo(String onlineSaleNo) {
        return sellerProfileService.countOnlineSaleNo(onlineSaleNo);
    }


    // 전화번호 중복 확인
    @GetMapping("/member/checkTel")
    @ResponseBody
    public int checkTel(String tel) {
        return sellerProfileService.countTel(tel);
    }





}
