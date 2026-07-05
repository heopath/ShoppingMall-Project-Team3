package kr.co.springkmarketapp.dao.my;

import kr.co.springkmarketapp.dto.my.MemberPointDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberPointDAO {
    int insertMemberPoint(MemberPointDTO memberPointDTO);
    MemberPointDTO selectMemberPoint(Integer pointNo);
    List<MemberPointDTO> selectMemberPointList();
    int updateMemberPoint(MemberPointDTO memberPointDTO);
    int deleteMemberPoint(Integer pointNo);
}
