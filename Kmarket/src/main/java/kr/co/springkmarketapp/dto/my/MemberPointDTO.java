package kr.co.springkmarketapp.dto.my;

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
public class MemberPointDTO {
    private Integer pointNo;
    private Integer memberNo;
    private Long orderNo;
    private String pointType;
    private Integer pointValue;
    private Integer balancePoint;
    private String reason;
    private LocalDate expireDate;
    private LocalDateTime regDate;
}
