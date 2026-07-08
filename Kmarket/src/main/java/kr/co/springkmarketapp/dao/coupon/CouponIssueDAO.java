package kr.co.springkmarketapp.dao.coupon;

import kr.co.springkmarketapp.dto.coupon.CouponIssueDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CouponIssueDAO {
    int insertCouponIssue(CouponIssueDTO couponIssueDTO);
    CouponIssueDTO selectCouponIssue(Long issueNo);
    List<CouponIssueDTO> selectCouponIssueList();
    List<CouponIssueDTO> selectCouponIssueListWithPaging(@Param("offset") int offset,
                                                         @Param("limit") int limit);
    List<CouponIssueDTO> selectCouponIssueListBySearchWithPaging(@Param("searchType") String searchType,
                                                                 @Param("keyword") String keyword,
                                                                 @Param("offset") int offset,
                                                                 @Param("limit") int limit);
    int countCouponIssueList();
    int countCouponIssueListBySearch(@Param("searchType") String searchType,
                                     @Param("keyword") String keyword);
    int updateCouponIssue(CouponIssueDTO couponIssueDTO);
    int updateCouponIssueStatus(@Param("issueNo") Long issueNo, @Param("status") String status);
    int deleteCouponIssue(Long issueNo);
}
