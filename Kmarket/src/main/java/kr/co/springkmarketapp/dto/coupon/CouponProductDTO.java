package kr.co.springkmarketapp.dto.coupon;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CouponProductDTO {
    private Integer couponNo;
    private Integer productNo;
}
