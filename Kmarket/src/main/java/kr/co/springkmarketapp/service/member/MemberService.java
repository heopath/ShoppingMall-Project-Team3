package kr.co.springkmarketapp.service.member;

import kr.co.springkmarketapp.dao.member.MemberDAO;
import kr.co.springkmarketapp.dto.member.MemberCheckDTO;
import kr.co.springkmarketapp.dto.member.MemberDTO;
import kr.co.springkmarketapp.entity.member.Member;
import kr.co.springkmarketapp.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final MemberDAO memberDAO;

    public Member insertMember(MemberDTO memberDTO) {
        String encoded = passwordEncoder.encode(memberDTO.getPassword());
        memberDTO.setPassword(encoded);
        // return memberDAO.insertMember(memberDTO);
        return memberRepository.save(memberDTO.toEntity());
    }

    public MemberDTO selectMember(Integer memberNo) {
        return memberDAO.selectMember(memberNo);
    }

    public List<MemberDTO> selectMemberList() {
        return memberDAO.selectMemberList();
    }

    public List<MemberDTO> selectMemberList(int offset, int limit) {
        return memberDAO.selectMemberListByPage(offset, limit);
    }

    public List<MemberDTO> selectMemberList(String searchType, String keyword) {
        return memberDAO.selectMemberListBySearch(searchType, keyword);
    }

    public List<MemberDTO> selectMemberList(String searchType, String keyword, int offset, int limit) {
        return memberDAO.selectMemberListBySearchAndPage(searchType, keyword, offset, limit);
    }

    public int countMemberList() {
        return memberDAO.countMemberList();
    }

    public int countMemberList(String searchType, String keyword) {
        return memberDAO.countMemberListBySearch(searchType, keyword);
    }

    public int updateMember(MemberDTO memberDTO) {
        return memberDAO.updateMember(memberDTO);
    }

    public int updateMemberBulk(List<Integer> memberNoList, String grade, String status) {
        return memberDAO.updateMemberBulk(memberNoList, grade, status);
    }


    public int deleteMember(Integer memberNo) {
        return memberDAO.deleteMember(memberNo);
    }

    public int getCount(MemberCheckDTO dto) {

        int count = 0;

        // JPA
        if (dto.getType().equals("userid")) {
            count = memberRepository.countByMemberId(dto.getValue());

        } else if (dto.getType().equals("email")) {

            count = memberRepository.countByEmail(dto.getValue());

        } else if (dto.getType().equals("hp")) {
            count = memberRepository.countByHp(dto.getValue());
        }
        return count;
    }
}
