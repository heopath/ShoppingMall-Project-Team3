package kr.co.springkmarketapp.entity.company;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "company_content")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyContent {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "content_no")
    private Integer contentNo;
    @Column(name = "content_type")
    private String contentType;
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "image_path")
    private String imagePath;
    @Column(name = "video_url")
    private String videoUrl;
    @Column(name = "category_name")
    private String categoryName;
    @Column(name = "use_yn")
    private String useYn;
    @Column(name = "reg_date")
    private LocalDateTime regDate;
}
