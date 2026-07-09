package kr.co.springkmarketapp.entity.admin;

import kr.co.springkmarketapp.dto.admin.BannerDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "banner")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Banner {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "banner_no")
    private Integer bannerNo;

    @Column(name = "banner_name", nullable = false, length = 100)
    private String bannerName;

    @Column(name = "banner_size", length = 50)
    private String bannerSize;

    @Column(name = "bg_color", length = 20)
    private String bgColor;

    @Column(name = "link_url")
    private String linkUrl;

    @Column(name = "banner_position", nullable = false, length = 50)
    private String bannerPosition;

    @Column(name = "image_path", nullable = false)
    private String imagePath;

    @Column(name = "sort_order", nullable = false)
    private Integer sortOrder;

    @Column(name = "start_datetime")
    private LocalDateTime startDatetime;

    @Column(name = "end_datetime")
    private LocalDateTime endDatetime;

    @Column(name = "use_yn", nullable = false, columnDefinition = "CHAR(1) DEFAULT 'Y'")
    private String useYn;

    @CreationTimestamp
    @Column(name = "reg_date", nullable = false, updatable = false)
    private LocalDateTime regDate;

    // 엔티티를 DTO로 변환하는 메서드
    public BannerDTO toDTO() {
        return BannerDTO.builder()
                .bannerNo(this.bannerNo)
                .bannerName(this.bannerName)
                .bannerSize(this.bannerSize)
                .bgColor(this.bgColor)
                .linkUrl(this.linkUrl)
                .bannerPosition(this.bannerPosition)
                .imagePath(this.imagePath)
                .sortOrder(this.sortOrder)
                .startDatetime(this.startDatetime)
                .endDatetime(this.endDatetime)
                .useYn(this.useYn)
                .regDate(this.regDate)
                .build();
    }
}
