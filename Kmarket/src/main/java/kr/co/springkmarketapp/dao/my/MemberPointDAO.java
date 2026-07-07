package kr.co.springkmarketapp.dao.my;

import kr.co.springkmarketapp.dto.my.MemberPointDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MemberPointDAO {
    int insertMemberPoint(MemberPointDTO memberPointDTO);
    MemberPointDTO selectMemberPoint(Integer pointNo);
    List<MemberPointDTO> selectMemberPointList();
    int updateMemberPoint(MemberPointDTO memberPointDTO);
    int deleteMemberPoint(Integer pointNo);
    List<MemberPointDTO> selectMemberPointListBySearch(@Param("searchType") String searchType,
                                                       @Param("keyword") String keyword);

    List<MemberPointDTO> selectMemberPointListWithPaging(@Param("offset") int offset,
                                                         @Param("limit") int limit);

    List<MemberPointDTO> selectMemberPointListBySearchWithPaging(@Param("searchType") String searchType,
                                                                 @Param("keyword") String keyword,
                                                                 @Param("offset") int offset,
                                                                 @Param("limit") int limit);

    List<MemberPointDTO> selectRevokeTargetPointList(@Param("pointNoList") List<Integer> pointNoList);

    Integer selectCurrentMemberPoint(@Param("memberNo") Integer memberNo);

    int updateMemberPointBalance(@Param("memberNo") Integer memberNo,
                                 @Param("point") Integer point);

    int updatePointTypeToRevoked(@Param("pointNo") Integer pointNo);
    int countMemberPointList();

    int countMemberPointListBySearch(@Param("searchType") String searchType,
                                     @Param("keyword") String keyword);



}
