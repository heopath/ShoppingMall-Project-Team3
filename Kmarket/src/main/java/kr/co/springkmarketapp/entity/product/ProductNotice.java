package kr.co.springkmarketapp.entity.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

@Entity
@Table(name = "product_notice")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductNotice {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_no")
    private Integer noticeNo;
    @Column(name = "product_no")
    private Integer productNo;
    @Column(name = "product_status")
    private String productStatus;
    @Column(name = "tax_type")
    private String taxType;
    @Column(name = "receipt_type")
    private String receiptType;
    @Column(name = "business_type")
    private String businessType;
    @Column(name = "origin")
    private String origin;
}
