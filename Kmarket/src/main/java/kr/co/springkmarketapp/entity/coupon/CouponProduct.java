package kr.co.springkmarketapp.entity.coupon;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "coupon_product")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(CouponProductId.class)
public class CouponProduct {

    @Id
    @Column(name = "coupon_no")
    private Integer couponNo;

    @Id
    @Column(name = "product_no")
    private Integer productNo;
}
