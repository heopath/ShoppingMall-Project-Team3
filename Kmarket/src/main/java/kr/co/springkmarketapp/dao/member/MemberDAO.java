package kr.co.springkmarketapp.dao.member;

import kr.co.springkmarketapp.dto.member.MemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MemberDAO {
    int insertMember(MemberDTO memberDTO);
    MemberDTO selectMember(Integer memberNo);
    List<MemberDTO> selectMemberList();
    List<MemberDTO> selectMemberListByPage(@Param("offset") int offset, @Param("limit") int limit);
    List<MemberDTO> selectMemberListBySearch(@Param("searchType") String searchType, @Param("keyword") String keyword);
    List<MemberDTO> selectMemberListBySearchAndPage(@Param("searchType") String searchType,
                                                    @Param("keyword") String keyword,
                                                    @Param("offset") int offset,
                                                    @Param("limit") int limit);
    int countMemberList();
    int countMemberListBySearch(@Param("searchType") String searchType, @Param("keyword") String keyword);
    int updateMember(MemberDTO memberDTO);
    int deleteMember(Integer memberNo);
}
