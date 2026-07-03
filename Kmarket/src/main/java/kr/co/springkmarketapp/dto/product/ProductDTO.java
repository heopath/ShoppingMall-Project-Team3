package kr.co.springkmarketapp.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

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
}
