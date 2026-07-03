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
}
