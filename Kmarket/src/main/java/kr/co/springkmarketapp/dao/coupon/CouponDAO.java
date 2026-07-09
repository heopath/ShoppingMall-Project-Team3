package kr.co.springkmarketapp.dao.coupon;

import kr.co.springkmarketapp.dto.coupon.CouponDTO;
import kr.co.springkmarketapp.dto.coupon.OrderCouponDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CouponDAO {
    int insertCoupon(CouponDTO couponDTO);
    CouponDTO selectCoupon(Integer couponNo);
    List<CouponDTO> selectCouponList();
    List<CouponDTO> selectCouponListWithPaging(@Param("offset") int offset, @Param("limit") int limit);
    List<CouponDTO> selectCouponListBySearchWithPaging(@Param("searchType") String searchType,
                                                       @Param("keyword") String keyword,
                                                       @Param("offset") int offset,
                                                       @Param("limit") int limit);
    int countCouponList();
    int countCouponListBySearch(@Param("searchType") String searchType,
                                @Param("keyword") String keyword);
    int updateCoupon(CouponDTO couponDTO);
    int updateCouponStatus(@Param("couponNo") Integer couponNo, @Param("status") String status);
    int deleteCoupon(Integer couponNo);

    // 주문 페이지에서 사용 가능한 쿠폰 목록 조회
    List<OrderCouponDTO> selectAvailableCoupons(
            @Param("memberNo") Integer memberNo
    );

    OrderCouponDTO selectAvailableCouponForOrder(
            @Param("memberNo") Integer memberNo,
            @Param("couponIssueNo") Long couponIssueNo
    );

    int updateCouponUsed(
            @Param("memberNo") Integer memberNo,
            @Param("couponIssueNo") Long couponIssueNo,
            @Param("orderNo") Long orderNo
    );
  
    int deleteCoupons(@Param("couponNos") List<Integer> couponNos);

    CouponDTO selectSellerProductCoupon(
            @Param("sellerNo") Integer sellerNo
    );

    int countIssuedSellerProductCoupon(
            @Param("memberNo") Integer memberNo,
            @Param("sellerNo") Integer sellerNo
    );

    int insertCouponIssue(
            @Param("issueCode") String issueCode,
            @Param("memberNo") Integer memberNo,
            @Param("couponNo") Integer couponNo
    );

    String selectSellerNameBySellerNo(@Param("sellerNo") Integer sellerNo);
}
