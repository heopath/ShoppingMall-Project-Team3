package kr.co.springkmarketapp.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryDetailDTO {
    private Integer deliveryNo;
    private String orderCode;
    private String receiverName;
    private String receiverHp;
    private String zip;
    private String addr1;
    private String addr2;
    private String courier;
    private String invoiceNo;
    private String memo;

    private List<DeliveryItemDTO> items;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DeliveryItemDTO {
        private Integer productNo;
        private String productName;
        private String productImage;
        private String companyName;
        private Integer price;
        private Integer quantity;
        private Integer deliveryFee;
        private Integer totalPrice;
    }
}