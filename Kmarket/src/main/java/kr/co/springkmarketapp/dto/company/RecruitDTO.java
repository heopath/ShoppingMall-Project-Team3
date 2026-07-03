package kr.co.springkmarketapp.dto.company;

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
public class RecruitDTO {
    private Integer recruitNo;
    private Integer writerNo;
    private String title;
    private String department;
    private String career;
    private String recruitType;
    private LocalDate startDate;
    private LocalDate endDate;
    private String note;
    private String status;
    private LocalDateTime regDate;
}
