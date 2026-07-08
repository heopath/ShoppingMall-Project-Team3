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
public class CouponIssueDTO {
    private Long issueNo;
    private String issueCode;
    private Integer couponNo;
    private Integer memberNo;
    private Long orderNo;
    private String status;
    private LocalDateTime usedDate;
    private LocalDateTime issueDate;

    // 관리자 화면 표시용
    private String couponCode;
    private String couponType;
    private String couponName;
    private String benefitType;
    private Integer benefitValue;
    private Integer minOrderPrice;
    private Integer maxDiscountPrice;
    private LocalDate startDate;
    private LocalDate endDate;
    private String caution;
    private String companyName;
    private String memberId;
    private String memberName;
}
