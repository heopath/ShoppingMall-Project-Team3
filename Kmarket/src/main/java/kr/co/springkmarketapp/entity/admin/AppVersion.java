package kr.co.springkmarketapp.entity.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "app_version")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppVersion {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "version_no")
    private Integer versionNo;
    @Column(name = "version_name")
    private String versionName;
    @Column(name = "writer_no")
    private Integer writerNo;
    @Column(name = "change_log")
    private String changeLog;
    @Column(name = "reg_date")
    private LocalDateTime regDate;
}
