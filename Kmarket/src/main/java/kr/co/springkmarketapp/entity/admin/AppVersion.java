package kr.co.springkmarketapp.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "app_version")
public class AppVersion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "version_no")
    private Integer versionNo;

    @Column(name = "version_name", nullable = false, length = 50)
    private String versionName;

    @Column(name = "writer_no")
    private Integer writerNo; // 로그인 기능 구현 전이므로 우선 상수로 처리하거나 Null 허용

    @Column(name = "change_log", nullable = false, columnDefinition = "TEXT")
    private String changeLog;

    @CreationTimestamp
    @Column(name = "reg_date", nullable = false, updatable = false)
    private LocalDateTime regDate;
}