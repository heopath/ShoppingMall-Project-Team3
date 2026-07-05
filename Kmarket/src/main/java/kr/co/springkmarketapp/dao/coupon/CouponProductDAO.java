package kr.co.springkmarketapp.dao.coupon;

import kr.co.springkmarketapp.dto.coupon.CouponProductDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CouponProductDAO {
    int insertCouponProduct(CouponProductDTO couponProductDTO);
    CouponProductDTO selectCouponProduct(CouponProductDTO couponProductDTO);
    List<CouponProductDTO> selectCouponProductList();
    int updateCouponProduct(CouponProductDTO couponProductDTO);
    int deleteCouponProduct(CouponProductDTO couponProductDTO);
}
