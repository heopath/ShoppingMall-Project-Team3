package kr.co.springkmarketapp.entity.coupon;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "coupon")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Coupon {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_no")
    private Integer couponNo;
    @Column(name = "coupon_code")
    private String couponCode;
    @Column(name = "seller_no")
    private Integer sellerNo;
    @Column(name = "coupon_type")
    private String couponType;
    @Column(name = "coupon_name")
    private String couponName;
    @Column(name = "benefit_type")
    private String benefitType;
    @Column(name = "benefit_value")
    private Integer benefitValue;
    @Column(name = "min_order_price")
    private Integer minOrderPrice;
    @Column(name = "max_discount_price")
    private Integer maxDiscountPrice;
    @Column(name = "issue_limit")
    private Integer issueLimit;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    @Column(name = "caution")
    private String caution;
    @Column(name = "status")
    private String status;
    @Column(name = "reg_date")
    private LocalDateTime regDate;
}
