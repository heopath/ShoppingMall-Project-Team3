package kr.co.springkmarketapp.dto.coupon;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductCouponModalDTO {

    private Integer couponNo;
    private String couponCode;
    private Integer sellerNo;

    private String couponName;
    private String couponType;
    private String benefitType;
    private Integer benefitValue;

    private Integer minOrderPrice;
    private Integer maxDiscountPrice;

    // 화면 출력용
    private String discountText;
    private String conditionText;

    // 구분값
    private boolean firstCoupon;
    private boolean issued;
}