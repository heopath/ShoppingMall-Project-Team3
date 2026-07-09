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

    private String orderCode;
    private LocalDateTime orderDate;
    private String companyName;
    private String deliveryStatus;

    private String receiverName;
    private String receiverHp;
    private String zip;
    private String addr1;
    private String addr2;
    private String memo;

    // 마이페이지 - 리뷰 있으면 상품평 쓰기 버튼 안 보이게 하기 위해 리뷰번호 조회
    private Long reviewNo;
}