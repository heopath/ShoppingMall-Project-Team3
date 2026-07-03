package kr.co.springkmarketapp.entity.cs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "notice")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Notice {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_no")
    private Integer noticeNo;
    @Column(name = "writer_no")
    private Integer writerNo;
    @Column(name = "notice_type")
    private String noticeType;
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "hit")
    private Integer hit;
    @Column(name = "reg_date")
    private LocalDateTime regDate;
    @Column(name = "update_date")
    private LocalDateTime updateDate;
}
