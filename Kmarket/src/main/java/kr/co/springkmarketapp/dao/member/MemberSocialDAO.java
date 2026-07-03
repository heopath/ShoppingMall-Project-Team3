package kr.co.springkmarketapp.dao.member;

import kr.co.springkmarketapp.dto.member.MemberSocialDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberSocialDAO {
    int insertMemberSocial(MemberSocialDTO memberSocialDTO);
    MemberSocialDTO selectMemberSocial(Long socialNo);
    List<MemberSocialDTO> selectMemberSocialList();
    int updateMemberSocial(MemberSocialDTO memberSocialDTO);
    int deleteMemberSocial(Long socialNo);
}
