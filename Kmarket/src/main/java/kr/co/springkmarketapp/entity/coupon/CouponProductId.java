package kr.co.springkmarketapp.entity.coupon;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CouponProductId implements Serializable {

    private Integer couponNo;
    private Integer productNo;
}
