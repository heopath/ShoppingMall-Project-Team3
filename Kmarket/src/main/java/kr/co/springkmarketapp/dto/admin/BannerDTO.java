package kr.co.springkmarketapp.dto.admin;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BannerDTO {
    private Integer bannerNo;
    private String bannerName;
    private String bannerSize;
    private String bgColor;
    private String linkUrl;
    private String bannerPosition; // MAIN1, PRODUCT1, MEMBER1, MY1 등
    private String imagePath;
    private Integer sortOrder;
    private LocalDateTime startDatetime;
    private LocalDateTime endDatetime;
    private String useYn; // Y: 노출, N: 숨김
    private LocalDateTime regDate;

    // 모달창에서 Multipart 파일 업로드를 바인딩하기 위해 필드 추가
    private MultipartFile bannerFile;
}
