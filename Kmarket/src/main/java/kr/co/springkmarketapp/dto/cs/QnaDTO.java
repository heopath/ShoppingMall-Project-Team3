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
public class QnaDTO {
    private Long qnaNo;
    private Integer memberNo;
    private Integer csCateNo;
    private String title;
    private String content;
    private String answer;
    private Integer answerMemberNo;
    private String status;
    private LocalDateTime regDate;
    private LocalDateTime answerDate;
}
