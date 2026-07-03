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
public class MemberSocialDTO {
    private Long socialNo;
    private Integer memberNo;
    private String provider;
    private String providerUserId;
    private String providerEmail;
    private LocalDateTime regDate;
}
