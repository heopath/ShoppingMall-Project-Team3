package kr.co.springkmarketapp.entity.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "product")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_no")
    private Integer productNo;
    @Column(name = "seller_no")
    private Integer sellerNo;
    @Column(name = "cate_no")
    private Integer cateNo;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "basic_desc")
    private String basicDesc;
    @Column(name = "brand")
    private String brand;
    @Column(name = "price")
    private Integer price;
    @Column(name = "discount_rate")
    private Integer discountRate;
    @Column(name = "point")
    private Integer point;
    @Column(name = "stock")
    private Integer stock;
    @Column(name = "delivery_fee")
    private Integer deliveryFee;
    @Column(name = "status")
    private String status;
    @Column(name = "view_count")
    private Integer viewCount;
    @Column(name = "sold_count")
    private Integer soldCount;
    @Column(name = "reg_date")
    private LocalDateTime regDate;
}
