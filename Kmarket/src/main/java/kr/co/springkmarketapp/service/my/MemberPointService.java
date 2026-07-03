package kr.co.springkmarketapp.service.my;

import kr.co.springkmarketapp.dao.my.MemberPointDAO;
import kr.co.springkmarketapp.dto.my.MemberPointDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberPointService {

    private final MemberPointDAO memberPointDAO;

    public int insertMemberPoint(MemberPointDTO memberPointDTO) {
        return memberPointDAO.insertMemberPoint(memberPointDTO);
    }

    public MemberPointDTO selectMemberPoint(Integer pointNo) {
        return memberPointDAO.selectMemberPoint(pointNo);
    }

    public List<MemberPointDTO> selectMemberPointList() {
        return memberPointDAO.selectMemberPointList();
    }

    public int updateMemberPoint(MemberPointDTO memberPointDTO) {
        return memberPointDAO.updateMemberPoint(memberPointDTO);
    }

    public int deleteMemberPoint(Integer pointNo) {
        return memberPointDAO.deleteMemberPoint(pointNo);
    }
}
