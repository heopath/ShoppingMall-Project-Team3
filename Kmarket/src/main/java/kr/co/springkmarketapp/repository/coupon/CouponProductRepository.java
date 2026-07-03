package kr.co.springkmarketapp.repository.coupon;

import kr.co.springkmarketapp.entity.coupon.CouponProduct;
import kr.co.springkmarketapp.entity.coupon.CouponProductId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponProductRepository extends JpaRepository<CouponProduct, CouponProductId> {
}
