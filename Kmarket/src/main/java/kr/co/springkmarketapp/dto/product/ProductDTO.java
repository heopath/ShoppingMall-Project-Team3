package kr.co.springkmarketapp.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Integer productNo;
    private Integer sellerNo;
    private Integer cateNo;
    private String productName;
    private String basicDesc;
    private String brand;
    private Integer price;
    private Integer discountRate;
    private Integer point;
    private Integer stock;
    private Integer deliveryFee;
    private String status;
    private Integer viewCount;
    private Integer soldCount;
    private LocalDateTime regDate;

    // 옵션 (동적, DB 저장용 아님 - 폼 전달용)
    private List<String> optionNames;
    private List<String> optionValues;

    // 제공고시 (폼 전달용)
    private String productStatus;
    private String taxType;
    private String receiptType;
    private String businessType;
    private String origin;

    // ▼ 목록 조회 전용 (JOIN 결과, DB 컬럼 아님)
    private String cateName;
    private String sellerName;
    private String thumbPath;
}
