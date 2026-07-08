package kr.co.springkmarketapp.dto.admin;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AdminOrderListDTO {
    private Long orderNo;            // 주문 PK (내부 로직 및 모달 트리거용)
    private String orderCode;        // 사용자 노출용 고유 주문번호
    private String memberId;         // 주문자 아이디 (Peformed by Member Table JOIN)
    private String ordererName;      // 주문자명
    private Integer itemTotalCount;  // 총 주문 상품 건수
    private Integer payPrice;        // 최종 실 결제 합계금액
    private String paymentMethod;    // 결제수단 (신용카드, 계좌이체 등)
    private String orderStatus;      // 주문상태 (결제완료, 입금대기, 주문취소 등)
    private LocalDateTime orderDate; // 주문일시
    private String deliveryStatus;   // 대표 배송상태 (배송대기, 배송중, 배송완료 등)
}