package kr.co.springkmarketapp.service.member;

import kr.co.springkmarketapp.dao.member.MemberDAO;
import kr.co.springkmarketapp.dto.member.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberDAO memberDAO;

    public int insertMember(MemberDTO memberDTO) {
        return memberDAO.insertMember(memberDTO);
    }

    public MemberDTO selectMember(Integer memberNo) {
        return memberDAO.selectMember(memberNo);
    }

    public List<MemberDTO> selectMemberList() {
        return memberDAO.selectMemberList();
    }

    public int updateMember(MemberDTO memberDTO) {
        return memberDAO.updateMember(memberDTO);
    }

    public int deleteMember(Integer memberNo) {
        return memberDAO.deleteMember(memberNo);
    }
}
