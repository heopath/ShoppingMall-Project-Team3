package kr.co.springkmarketapp.entity.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
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
    @Column(name = "banner_name")
    private String bannerName;
    @Column(name = "banner_size")
    private String bannerSize;
    @Column(name = "bg_color")
    private String bgColor;
    @Column(name = "link_url")
    private String linkUrl;
    @Column(name = "banner_position")
    private String bannerPosition;
    @Column(name = "image_path")
    private String imagePath;
    @Column(name = "sort_order")
    private Integer sortOrder;
    @Column(name = "start_datetime")
    private LocalDateTime startDatetime;
    @Column(name = "end_datetime")
    private LocalDateTime endDatetime;
    @Column(name = "use_yn")
    private String useYn;
    @Column(name = "reg_date")
    private LocalDateTime regDate;
}
