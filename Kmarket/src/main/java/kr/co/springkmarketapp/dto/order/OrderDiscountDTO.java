package kr.co.springkmarketapp.dto.order;

import kr.co.springkmarketapp.dto.coupon.OrderCouponDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDiscountDTO {

    private Integer availablePoint;

    private List<OrderCouponDTO> coupons;
}