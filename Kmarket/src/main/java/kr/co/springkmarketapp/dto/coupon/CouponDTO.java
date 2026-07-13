package kr.co.springkmarketapp.dto.coupon;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CouponDTO {
    private Integer couponNo;
    private String couponCode;
    private Integer sellerNo;
    private String couponType;
    private String couponName;
    private String benefitType;
    private Integer benefitValue;
    private Integer minOrderPrice;
    private Integer maxDiscountPrice;
    private Integer issueLimit;
    private LocalDate startDate;
    private LocalDate endDate;
    private String caution;
    private String status;
    private LocalDateTime regDate;

    // 관리자 화면 표시용
    private String companyName;
    private Integer issuedCount;
    private Integer usedCount;
}
