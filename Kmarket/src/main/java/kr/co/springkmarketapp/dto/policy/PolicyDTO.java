package kr.co.springkmarketapp.dto.policy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PolicyDTO {
    private Integer policyNo;
    private String policyType;
    private String title;
    private String content;
    private String requiredYn;
    private String useYn;
    private LocalDateTime updateDate;
}
