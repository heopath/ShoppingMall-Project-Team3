package kr.co.springkmarketapp.service.coupon;

import kr.co.springkmarketapp.dao.coupon.CouponDAO;
import kr.co.springkmarketapp.dto.coupon.CouponDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public List<CouponDTO> selectCouponList(int offset, int limit) {
        return couponDAO.selectCouponListWithPaging(offset, limit);
    }

    public List<CouponDTO> selectCouponList(String searchType, String keyword, int offset, int limit) {
        return couponDAO.selectCouponListBySearchWithPaging(searchType, keyword, offset, limit);
    }

    public int countCouponList() {
        return couponDAO.countCouponList();
    }

    public int countCouponList(String searchType, String keyword) {
        return couponDAO.countCouponListBySearch(searchType, keyword);
    }

    public int updateCoupon(CouponDTO couponDTO) {
        return couponDAO.updateCoupon(couponDTO);
    }

    public int updateCouponStatus(Integer couponNo, String status) {
        return couponDAO.updateCouponStatus(couponNo, status);
    }

    public int deleteCoupon(Integer couponNo) {
        return couponDAO.deleteCoupon(couponNo);
    }

    @Transactional
    public int deleteCoupons(List<Integer> couponNos) {
        return couponDAO.deleteCoupons(couponNos);
    }
}
