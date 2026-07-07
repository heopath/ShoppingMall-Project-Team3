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

    int updateMemberBulk(@Param("memberNoList") List<Integer> memberNoList,
                         @Param("grade") String grade,
                         @Param("status") String status);

    int countByNameAndEmail(@Param("name") String name,
                            @Param("email") String email);

    String findMemberId(@Param("name") String name,
                        @Param("email") String email);

    int countByMemberIdAndEmail(@Param("memberId") String memberId,
                                @Param("email") String email);

    int changePassword(@Param("memberId") String memberId,
                       @Param("encoded") String encoded);

    MemberDTO selectMemberByMemberId(@Param("memberId") String memberId);
}
