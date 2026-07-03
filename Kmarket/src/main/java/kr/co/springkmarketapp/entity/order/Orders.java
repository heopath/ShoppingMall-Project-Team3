package kr.co.springkmarketapp.entity.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Orders {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_no")
    private Long orderNo;
    @Column(name = "order_code")
    private String orderCode;
    @Column(name = "member_no")
    private Integer memberNo;
    @Column(name = "orderer_name")
    private String ordererName;
    @Column(name = "orderer_hp")
    private String ordererHp;
    @Column(name = "total_price")
    private Integer totalPrice;
    @Column(name = "discount_price")
    private Integer discountPrice;
    @Column(name = "coupon_discount")
    private Integer couponDiscount;
    @Column(name = "delivery_fee")
    private Integer deliveryFee;
    @Column(name = "point_use")
    private Integer pointUse;
    @Column(name = "point_save")
    private Integer pointSave;
    @Column(name = "pay_price")
    private Integer payPrice;
    @Column(name = "payment_method")
    private String paymentMethod;
    @Column(name = "payment_status")
    private String paymentStatus;
    @Column(name = "paid_date")
    private LocalDateTime paidDate;
    @Column(name = "cancel_date")
    private LocalDateTime cancelDate;
    @Column(name = "order_status")
    private String orderStatus;
    @Column(name = "order_date")
    private LocalDateTime orderDate;
}
