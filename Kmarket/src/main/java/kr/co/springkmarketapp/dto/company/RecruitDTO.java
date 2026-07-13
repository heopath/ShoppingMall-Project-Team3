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
    private String writerId;
    private String title;
    private String department;
    private String career;
    private String recruitType;
    private LocalDate startDate;
    private LocalDate endDate;
    private String note;
    private String status;
    private LocalDateTime regDate;

    public boolean isOpen() {
        LocalDate today = LocalDate.now();

        return "모집중".equals(status)
                && (startDate == null || !startDate.isAfter(today))
                && (endDate == null || !endDate.isBefore(today));
    }

    public boolean isUpcoming() {
        return "모집중".equals(status)
                && startDate != null
                && startDate.isAfter(LocalDate.now());
    }

    public String getDisplayStatus() {
        if (isOpen()) {
            return "모집중";
        }

        if (isUpcoming()) {
            return "모집예정";
        }

        return "모집종료";
    }
}
