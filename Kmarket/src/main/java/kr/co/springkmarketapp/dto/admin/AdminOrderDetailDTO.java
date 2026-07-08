package kr.co.springkmarketapp.dto.admin;

import lombok.Data;
import java.util.List;

@Data
public class AdminOrderDetailDTO {
    // 주문 및 결제 마스터
    private String orderCode;
    private String paymentMethod;
    private String ordererName;
    private String ordererHp;
    private Integer totalPrice;
    private Integer discountPrice;
    private Integer deliveryFee;
    private Integer payPrice;

    // 배송지 정보
    private String receiverName;
    private String receiverHp;
    private String zip;
    private String addr1;
    private String addr2;

    // 팝업 내부의 상품 목록 테이블 바인딩용 리스트
    private List<OrderItemDetail> items;

    @Data
    public static class OrderItemDetail {
        private Integer productNo;
        private String productName;
        private String productImage;
        private String companyName; // seller_profile JOIN 결과 (판매자 상호)
        private Integer price;
        private Integer discountRate;
        private Integer quantity;
        private Integer totalPrice;
    }
}