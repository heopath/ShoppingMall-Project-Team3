package kr.co.springkmarketapp.dto.cs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoticeDTO {
    private Integer noticeNo;
    private Integer writerNo;
    private String noticeType;
    private String title;
    private String content;
    private Integer hit;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;
}