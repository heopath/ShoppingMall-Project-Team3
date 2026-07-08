package kr.co.springkmarketapp.dao.coupon;

import kr.co.springkmarketapp.dto.coupon.CouponDTO;
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
    int deleteCoupons(@Param("couponNos") List<Integer> couponNos);
}
