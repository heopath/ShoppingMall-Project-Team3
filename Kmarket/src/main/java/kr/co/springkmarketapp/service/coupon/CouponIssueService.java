package kr.co.springkmarketapp.service.coupon;

import kr.co.springkmarketapp.dao.coupon.CouponIssueDAO;
import kr.co.springkmarketapp.dto.coupon.CouponIssueDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponIssueService {

    private final CouponIssueDAO couponIssueDAO;

    public int insertCouponIssue(CouponIssueDTO couponIssueDTO) {
        return couponIssueDAO.insertCouponIssue(couponIssueDTO);
    }

    public CouponIssueDTO selectCouponIssue(Long issueNo) {
        return couponIssueDAO.selectCouponIssue(issueNo);
    }

    public List<CouponIssueDTO> selectCouponIssueList() {
        return couponIssueDAO.selectCouponIssueList();
    }

    public List<CouponIssueDTO> selectCouponIssueList(int offset, int limit) {
        return couponIssueDAO.selectCouponIssueListWithPaging(offset, limit);
    }

    public List<CouponIssueDTO> selectCouponIssueList(String searchType, String keyword, int offset, int limit) {
        return couponIssueDAO.selectCouponIssueListBySearchWithPaging(searchType, keyword, offset, limit);
    }

    public int countCouponIssueList() {
        return couponIssueDAO.countCouponIssueList();
    }

    public int countCouponIssueList(String searchType, String keyword) {
        return couponIssueDAO.countCouponIssueListBySearch(searchType, keyword);
    }

    public int updateCouponIssue(CouponIssueDTO couponIssueDTO) {
        return couponIssueDAO.updateCouponIssue(couponIssueDTO);
    }

    public int updateCouponIssueStatus(Long issueNo, String status) {
        return couponIssueDAO.updateCouponIssueStatus(issueNo, status);
    }

    public int deleteCouponIssue(Long issueNo) {
        return couponIssueDAO.deleteCouponIssue(issueNo);
    }
}
