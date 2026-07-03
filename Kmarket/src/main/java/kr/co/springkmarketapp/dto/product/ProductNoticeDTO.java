package kr.co.springkmarketapp.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductNoticeDTO {
    private Integer noticeNo;
    private Integer productNo;
    private String productStatus;
    private String taxType;
    private String receiptType;
    private String businessType;
    private String origin;
}
