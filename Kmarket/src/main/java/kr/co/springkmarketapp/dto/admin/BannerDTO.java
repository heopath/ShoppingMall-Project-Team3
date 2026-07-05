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
public class BannerDTO {
    private Integer bannerNo;
    private String bannerName;
    private String bannerSize;
    private String bgColor;
    private String linkUrl;
    private String bannerPosition;
    private String imagePath;
    private Integer sortOrder;
    private LocalDateTime startDatetime;
    private LocalDateTime endDatetime;
    private String useYn;
    private LocalDateTime regDate;
}
