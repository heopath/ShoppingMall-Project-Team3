package kr.co.springkmarketapp.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryDTO {
    private Integer deliveryNo;
    private Long orderNo;
    private Integer sellerNo;
    private String receiverName;
    private String receiverHp;
    private String zip;
    private String addr1;
    private String addr2;
    private String courier;
    private String invoiceNo;
    private Integer deliveryFee;
    private String deliveryStatus;
    private String memo;
    private LocalDateTime receiptDate;
    private LocalDateTime shippedDate;
    private LocalDateTime deliveredDate;
}
