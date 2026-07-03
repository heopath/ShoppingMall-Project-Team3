package kr.co.springkmarketapp.dao.member;

import kr.co.springkmarketapp.dto.member.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberDAO {
    int insertMember(MemberDTO memberDTO);
    MemberDTO selectMember(Integer memberNo);
    List<MemberDTO> selectMemberList();
    int updateMember(MemberDTO memberDTO);
    int deleteMember(Integer memberNo);
}
