package kr.co.springkmarketapp.repository.coupon;

import kr.co.springkmarketapp.entity.coupon.CouponIssue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponIssueRepository extends JpaRepository<CouponIssue, Long> {
}
