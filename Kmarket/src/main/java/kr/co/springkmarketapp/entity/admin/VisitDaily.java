package kr.co.springkmarketapp.entity.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "visit_daily")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VisitDaily {


    @Id
    @Column(name = "visit_date")
    private LocalDate visitDate;
    @Column(name = "visit_count")
    private Integer visitCount;
}
