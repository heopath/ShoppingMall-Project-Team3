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

    // 포인트관리 화면 출력용
    private String memberId;
    private String name;
    private String revokedYn;
}
