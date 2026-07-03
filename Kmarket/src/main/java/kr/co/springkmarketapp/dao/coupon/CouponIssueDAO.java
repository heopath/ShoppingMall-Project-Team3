package kr.co.springkmarketapp.dao.coupon;

import kr.co.springkmarketapp.dto.coupon.CouponIssueDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CouponIssueDAO {
    int insertCouponIssue(CouponIssueDTO couponIssueDTO);
    CouponIssueDTO selectCouponIssue(Long issueNo);
    List<CouponIssueDTO> selectCouponIssueList();
    int updateCouponIssue(CouponIssueDTO couponIssueDTO);
    int deleteCouponIssue(Long issueNo);
}
