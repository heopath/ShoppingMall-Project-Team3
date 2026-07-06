package kr.co.springkmarketapp.controller.admin;

import kr.co.springkmarketapp.dto.member.MemberDTO;
import kr.co.springkmarketapp.service.member.MemberService;
import kr.co.springkmarketapp.service.my.MemberPointService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AdminMemberController {

    private final MemberService memberService;
    private final MemberPointService memberPointService;

    @GetMapping("/admin/member/list")
    public String list(@RequestParam(required = false) String searchType,
                       @RequestParam(required = false) String keyword,
                       @RequestParam(defaultValue = "1") int page,
                       Model model) {
        int pageSize = 10;
        boolean hasKeyword = keyword != null && !keyword.isBlank();
        int totalCount = hasKeyword ? memberService.countMemberList(searchType, keyword) : memberService.countMemberList();
        int totalPages = Math.max(1, (int) Math.ceil((double) totalCount / pageSize));
        int currentPage = Math.min(Math.max(page, 1), totalPages);
        int offset = (currentPage - 1) * pageSize;
        int pageBlockSize = 5;
        int startPage = ((currentPage - 1) / pageBlockSize) * pageBlockSize + 1;
        int endPage = Math.min(startPage + pageBlockSize - 1, totalPages);

        model.addAttribute("memberList", hasKeyword
            ? memberService.selectMemberList(searchType, keyword, offset, pageSize)
            : memberService.selectMemberList(offset, pageSize));
        model.addAttribute("searchType", searchType);
        model.addAttribute("keyword", keyword);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("hasPrevPage", currentPage > 1);
        model.addAttribute("hasNextPage", currentPage < totalPages);
        return "admin/member/list";
    }

    // AJAX 전용: 등급 변경 (페이지 이동 없이 성공/실패만 응답)
    @PostMapping("/admin/member/grade")
    @ResponseBody
    public ResponseEntity<Void> updateGrade(@RequestParam Integer memberNo, @RequestParam String grade) {
        MemberDTO member = memberService.selectMember(memberNo);
        if (member == null) {
            return ResponseEntity.notFound().build();
        }
        member.setGrade(grade);
        memberService.updateMember(member);
        return ResponseEntity.ok().build();
    }

    // AJAX 전용: 상태 변경 (페이지 이동 없이 성공/실패만 응답)
    @PostMapping("/admin/member/status")
    @ResponseBody
    public ResponseEntity<Void> updateStatus(@RequestParam Integer memberNo, @RequestParam String status) {
        MemberDTO member = memberService.selectMember(memberNo);
        if (member == null) {
            return ResponseEntity.notFound().build();
        }
        member.setStatus(status);
        memberService.updateMember(member);
        return ResponseEntity.ok().build();
    }

    // AJAX 전용: 회원정보 수정 모달 저장 (이름/성별/이메일/휴대폰/주소)
    // 등급, 상태, 포인트, 비밀번호는 이 화면에서 건드리지 않도록 기존 값을 그대로 유지
    @PostMapping("/admin/member/update")
    @ResponseBody
    public ResponseEntity<Void> updateMemberInfo(@RequestParam Integer memberNo,
                                                 @RequestParam String name,
                                                 @RequestParam String gender,
                                                 @RequestParam String email,
                                                 @RequestParam String hp,
                                                 @RequestParam(required = false) String zip,
                                                 @RequestParam(required = false) String addr1,
                                                 @RequestParam(required = false) String addr2) {
        MemberDTO member = memberService.selectMember(memberNo);
        if (member == null) {
            return ResponseEntity.notFound().build();
        }
        member.setName(name);
        member.setGender(gender);
        member.setEmail(email);
        member.setHp(hp);
        member.setZip(zip);
        member.setAddr1(addr1);
        member.setAddr2(addr2);
        memberService.updateMember(member);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/admin/member/delete")
    public String delete(@RequestParam Integer memberNo) {
        memberService.deleteMember(memberNo);
        return "redirect:/admin/member/list";
    }

    @GetMapping("/admin/member/point")
    public String point(Model model) {
        model.addAttribute("pointList", memberPointService.selectMemberPointList());
        model.addAttribute("memberList", memberService.selectMemberList());
        return "admin/member/point";
    }
}
