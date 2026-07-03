package kr.co.springkmarketapp.service.coupon;

import kr.co.springkmarketapp.dao.coupon.CouponDAO;
import kr.co.springkmarketapp.dto.coupon.CouponDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponDAO couponDAO;

    public int insertCoupon(CouponDTO couponDTO) {
        return couponDAO.insertCoupon(couponDTO);
    }

    public CouponDTO selectCoupon(Integer couponNo) {
        return couponDAO.selectCoupon(couponNo);
    }

    public List<CouponDTO> selectCouponList() {
        return couponDAO.selectCouponList();
    }

    public int updateCoupon(CouponDTO couponDTO) {
        return couponDAO.updateCoupon(couponDTO);
    }

    public int deleteCoupon(Integer couponNo) {
        return couponDAO.deleteCoupon(couponNo);
    }
}
