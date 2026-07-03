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
public class OrderItemDTO {
    private Long orderItemNo;
    private Long orderNo;
    private Integer deliveryNo;
    private Integer productNo;
    private Integer sellerNo;
    private String productName;
    private String productImage;
    private Integer price;
    private Integer discountRate;
    private Integer couponDiscount;
    private Integer point;
    private Integer quantity;
    private Integer totalPrice;
    private String itemStatus;
    private LocalDateTime purchaseConfirmDate;
}
