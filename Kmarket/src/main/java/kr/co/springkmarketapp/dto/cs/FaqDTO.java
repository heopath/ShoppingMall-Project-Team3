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
public class FaqDTO {
    private Integer faqNo;
    private Integer csCateNo;
    private Integer writerNo;
    private String title;
    private String content;
    private Integer hit;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;

    private String cateName;
    private String parentCateName;
}
