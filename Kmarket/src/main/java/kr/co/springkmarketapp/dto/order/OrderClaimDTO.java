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
public class OrderClaimDTO {
    private Long claimNo;
    private Long orderItemNo;
    private Integer memberNo;
    private String claimType;
    private String claimReason;
    private String detailReason;
    private String status;
    private LocalDateTime requestDate;
    private LocalDateTime processDate;
}
