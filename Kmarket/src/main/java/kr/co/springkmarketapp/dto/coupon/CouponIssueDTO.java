package kr.co.springkmarketapp.dto.coupon;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
}
