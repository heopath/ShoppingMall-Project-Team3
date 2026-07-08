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
public class OrderSaveDTO {

    private Long orderNo;
    private String orderCode;

    private Integer memberNo;

    private String ordererName;
    private String ordererHp;

    private Integer totalPrice;
    private Integer discountPrice;
    private Integer couponDiscount;
    private Integer deliveryFee;
    private Integer pointUse;
    private Integer pointSave;
    private Integer payPrice;

    private String paymentMethod;
    private String paymentStatus;

    private LocalDateTime paidDate;
    private LocalDateTime cancelDate;

    private String orderStatus;
    private LocalDateTime orderDate;
}