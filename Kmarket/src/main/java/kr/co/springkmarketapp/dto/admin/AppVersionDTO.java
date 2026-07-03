package kr.co.springkmarketapp.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppVersionDTO {
    private Integer versionNo;
    private String versionName;
    private Integer writerNo;
    private String changeLog;
    private LocalDateTime regDate;
}
