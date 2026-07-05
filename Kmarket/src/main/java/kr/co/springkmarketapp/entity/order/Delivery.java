package kr.co.springkmarketapp.entity.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "delivery")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Delivery {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_no")
    private Integer deliveryNo;
    @Column(name = "order_no")
    private Long orderNo;
    @Column(name = "seller_no")
    private Integer sellerNo;
    @Column(name = "receiver_name")
    private String receiverName;
    @Column(name = "receiver_hp")
    private String receiverHp;
    @Column(name = "zip")
    private String zip;
    @Column(name = "addr1")
    private String addr1;
    @Column(name = "addr2")
    private String addr2;
    @Column(name = "courier")
    private String courier;
    @Column(name = "invoice_no")
    private String invoiceNo;
    @Column(name = "delivery_fee")
    private Integer deliveryFee;
    @Column(name = "delivery_status")
    private String deliveryStatus;
    @Column(name = "memo")
    private String memo;
    @Column(name = "receipt_date")
    private LocalDateTime receiptDate;
    @Column(name = "shipped_date")
    private LocalDateTime shippedDate;
    @Column(name = "delivered_date")
    private LocalDateTime deliveredDate;
}
