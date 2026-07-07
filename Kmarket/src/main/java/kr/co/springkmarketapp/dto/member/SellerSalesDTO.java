package kr.co.springkmarketapp.dto.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SellerSalesDTO {
    private Integer sellerNo;
    private String companyName;
    private String bizNo;

    private Integer orderCount;
    private Integer paymentCompleteCount;
    private Integer deliveryIngCount;
    private Integer deliveryCompleteCount;
    private Integer purchaseConfirmCount;

    private Long orderTotal;
    private Long salesTotal;
}
