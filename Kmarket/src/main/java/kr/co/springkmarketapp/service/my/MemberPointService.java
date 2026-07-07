package kr.co.springkmarketapp.service.my;

import kr.co.springkmarketapp.dao.my.MemberPointDAO;
import kr.co.springkmarketapp.dto.my.MemberPointDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    public List<MemberPointDTO> selectMemberPointList(String searchType, String keyword) {
        return memberPointDAO.selectMemberPointListBySearch(searchType, keyword);
    }
    public int updateMemberPoint(MemberPointDTO memberPointDTO) {
        return memberPointDAO.updateMemberPoint(memberPointDTO);
    }

    public int deleteMemberPoint(Integer pointNo) {
        return memberPointDAO.deleteMemberPoint(pointNo);
    }

    public List<MemberPointDTO> selectMemberPointList(int offset, int limit) {
        return memberPointDAO.selectMemberPointListWithPaging(offset, limit);
    }

    public List<MemberPointDTO> selectMemberPointList(String searchType, String keyword, int offset, int limit) {
        return memberPointDAO.selectMemberPointListBySearchWithPaging(searchType, keyword, offset, limit);
    }

    public int countMemberPointList() {
        return memberPointDAO.countMemberPointList();
    }

    public int countMemberPointList(String searchType, String keyword) {
        return memberPointDAO.countMemberPointListBySearch(searchType, keyword);
    }

    @Transactional
    public void revokeSelectedPoint(List<Integer> pointNoList) {

        List<MemberPointDTO> pointList = memberPointDAO.selectRevokeTargetPointList(pointNoList);

        if (pointList == null || pointList.isEmpty()) {
            throw new IllegalArgumentException("회수 가능한 포인트 내역이 없습니다.");
        }

        for (MemberPointDTO point : pointList) {
            Integer memberNo = point.getMemberNo();
            Integer pointValue = point.getPointValue();

            if (memberNo == null || pointValue == null || pointValue <= 0) {
                continue;
            }

            Integer currentPoint = memberPointDAO.selectCurrentMemberPoint(memberNo);

            if (currentPoint == null) {
                currentPoint = 0;
            }

            int newBalancePoint = Math.max(0, currentPoint - pointValue);

            // 1. 회원 보유 포인트 차감
            memberPointDAO.updateMemberPointBalance(memberNo, newBalancePoint);

            // 2. 기존 포인트 내역을 회수 상태로 변경
            memberPointDAO.updatePointTypeToRevoked(point.getPointNo());
        }
    }

}
