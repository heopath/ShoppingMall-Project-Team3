package kr.co.springkmarketapp.dto.coupon;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderCouponDTO {

    // coupon_issue.issue_no
    private Long couponIssueNo;

    // coupon.coupon_no
    private Integer couponNo;

    // 판매자 쿠폰이면 값 있음, 전체 쿠폰이면 null
    private Integer sellerNo;

    private String couponName;

    // ORDER, PRODUCT, FREE_SHIPPING
    private String couponType;

    // AMOUNT, RATE, FREE_SHIPPING
    private String benefitType;

    // 할인 금액 또는 할인율
    private Integer benefitValue;

    private Integer minOrderPrice;
    private Integer maxDiscountPrice;
}