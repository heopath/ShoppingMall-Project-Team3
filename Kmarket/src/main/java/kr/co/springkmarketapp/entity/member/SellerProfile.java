package kr.co.springkmarketapp.entity.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "seller_profile")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SellerProfile {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seller_no")
    private Integer sellerNo;
    @Column(name = "member_no")
    private Integer memberNo;
    @Column(name = "company_name")
    private String companyName;
    @Column(name = "ceo_name")
    private String ceoName;
    @Column(name = "biz_no")
    private String bizNo;
    @Column(name = "online_sale_no")
    private String onlineSaleNo;
    @Column(name = "tel")
    private String tel;
    @Column(name = "fax")
    private String fax;
    @Column(name = "zip")
    private String zip;
    @Column(name = "addr1")
    private String addr1;
    @Column(name = "addr2")
    private String addr2;
    @Column(name = "status")
    private String status;
    @Column(name = "reg_date")
    private LocalDateTime regDate;
}
