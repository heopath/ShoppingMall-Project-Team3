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
    int updateCoupon(CouponDTO couponDTO);
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
}
