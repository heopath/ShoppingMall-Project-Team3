package kr.co.springkmarketapp.dto.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SellerProfileDTO {
    private Integer sellerNo;
    private Integer memberNo;

    //상점등록 모달에서 등록받을 값
    private String memberId;
    private String password;

    // 마이페이지 주문상세 모달 조회 추가필드
    private String companyName;
    private String ceoName;
    private String bizNo;
    private String onlineSaleNo;
    private String tel;
    private String fax;
    private String zip;
    private String addr1;
    private String addr2;
    private String status;
    private LocalDateTime regDate;

    // 마이페이지 판매자 상세 모달 조회 추가필드
    private String ceo;
    private String hp;
    private String email;
    private String bizRegNo;
    private String grade;
}
