package kr.co.springkmarketapp.dao.coupon;

import kr.co.springkmarketapp.dto.coupon.CouponDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CouponDAO {
    int insertCoupon(CouponDTO couponDTO);
    CouponDTO selectCoupon(Integer couponNo);
    List<CouponDTO> selectCouponList();
    int updateCoupon(CouponDTO couponDTO);
    int deleteCoupon(Integer couponNo);
}
