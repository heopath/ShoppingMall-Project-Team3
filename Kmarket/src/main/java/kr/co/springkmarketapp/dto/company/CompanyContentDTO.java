package kr.co.springkmarketapp.dto.company;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyContentDTO {
    private Integer contentNo;
    private String contentType;
    private String title;
    private String content;
    private String imagePath;
    private String videoUrl;
    private String categoryName;
    private String useYn;
    private LocalDateTime regDate;
}
