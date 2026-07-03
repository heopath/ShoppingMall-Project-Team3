package kr.co.springkmarketapp.service.coupon;

import kr.co.springkmarketapp.dao.coupon.CouponProductDAO;
import kr.co.springkmarketapp.dto.coupon.CouponProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponProductService {

    private final CouponProductDAO couponProductDAO;

    public int insertCouponProduct(CouponProductDTO couponProductDTO) {
        return couponProductDAO.insertCouponProduct(couponProductDTO);
    }

    public CouponProductDTO selectCouponProduct(CouponProductDTO couponProductDTO) {
        return couponProductDAO.selectCouponProduct(couponProductDTO);
    }

    public List<CouponProductDTO> selectCouponProductList() {
        return couponProductDAO.selectCouponProductList();
    }

    public int updateCouponProduct(CouponProductDTO couponProductDTO) {
        return couponProductDAO.updateCouponProduct(couponProductDTO);
    }

    public int deleteCouponProduct(CouponProductDTO couponProductDTO) {
        return couponProductDAO.deleteCouponProduct(couponProductDTO);
    }
}
