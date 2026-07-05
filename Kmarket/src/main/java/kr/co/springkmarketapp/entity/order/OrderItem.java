package kr.co.springkmarketapp.entity.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "order_item")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_no")
    private Long orderItemNo;
    @Column(name = "order_no")
    private Long orderNo;
    @Column(name = "delivery_no")
    private Integer deliveryNo;
    @Column(name = "product_no")
    private Integer productNo;
    @Column(name = "seller_no")
    private Integer sellerNo;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "product_image")
    private String productImage;
    @Column(name = "price")
    private Integer price;
    @Column(name = "discount_rate")
    private Integer discountRate;
    @Column(name = "coupon_discount")
    private Integer couponDiscount;
    @Column(name = "point")
    private Integer point;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "total_price")
    private Integer totalPrice;
    @Column(name = "item_status")
    private String itemStatus;
    @Column(name = "purchase_confirm_date")
    private LocalDateTime purchaseConfirmDate;
}
