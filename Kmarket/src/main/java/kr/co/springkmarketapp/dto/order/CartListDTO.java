package kr.co.springkmarketapp.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartListDTO {

    private Integer cartNo;
    private Integer productNo;
    private Integer sellerNo;

    private String productName;
    private String basicDesc;
    private String imagePath;

    private Integer price;
    private Integer discountRate;
    private Integer point;
    private Integer deliveryFee;
    private Integer quantity;

    // 예: 색상 : 블랙 / 사이즈 : M
    private String optionText;
}