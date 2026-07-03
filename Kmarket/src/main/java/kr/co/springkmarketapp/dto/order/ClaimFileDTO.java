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
public class ClaimFileDTO {
    private Long claimFileNo;
    private Long claimNo;
    private String oriName;
    private String newName;
    private LocalDateTime regDate;
}
