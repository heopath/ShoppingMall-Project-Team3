package kr.co.springkmarketapp.service.member;

import kr.co.springkmarketapp.dao.member.MemberSocialDAO;
import kr.co.springkmarketapp.dto.member.MemberSocialDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberSocialService {

    private final MemberSocialDAO memberSocialDAO;

    public int insertMemberSocial(MemberSocialDTO memberSocialDTO) {
        return memberSocialDAO.insertMemberSocial(memberSocialDTO);
    }

    public MemberSocialDTO selectMemberSocial(Long socialNo) {
        return memberSocialDAO.selectMemberSocial(socialNo);
    }

    public List<MemberSocialDTO> selectMemberSocialList() {
        return memberSocialDAO.selectMemberSocialList();
    }

    public int updateMemberSocial(MemberSocialDTO memberSocialDTO) {
        return memberSocialDAO.updateMemberSocial(memberSocialDTO);
    }

    public int deleteMemberSocial(Long socialNo) {
        return memberSocialDAO.deleteMemberSocial(socialNo);
    }
}
