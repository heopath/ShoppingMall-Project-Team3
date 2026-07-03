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

    public int updateCouponIssue(CouponIssueDTO couponIssueDTO) {
        return couponIssueDAO.updateCouponIssue(couponIssueDTO);
    }

    public int deleteCouponIssue(Long issueNo) {
        return couponIssueDAO.deleteCouponIssue(issueNo);
    }
}
