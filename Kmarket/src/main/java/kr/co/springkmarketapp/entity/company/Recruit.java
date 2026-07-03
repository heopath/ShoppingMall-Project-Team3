package kr.co.springkmarketapp.entity.company;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "recruit")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Recruit {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recruit_no")
    private Integer recruitNo;
    @Column(name = "writer_no")
    private Integer writerNo;
    @Column(name = "title")
    private String title;
    @Column(name = "department")
    private String department;
    @Column(name = "career")
    private String career;
    @Column(name = "recruit_type")
    private String recruitType;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    @Column(name = "note")
    private String note;
    @Column(name = "status")
    private String status;
    @Column(name = "reg_date")
    private LocalDateTime regDate;
}
