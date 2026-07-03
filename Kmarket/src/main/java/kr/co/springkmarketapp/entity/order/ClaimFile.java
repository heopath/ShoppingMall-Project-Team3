package kr.co.springkmarketapp.entity.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "claim_file")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClaimFile {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "claim_file_no")
    private Long claimFileNo;
    @Column(name = "claim_no")
    private Long claimNo;
    @Column(name = "ori_name")
    private String oriName;
    @Column(name = "new_name")
    private String newName;
    @Column(name = "reg_date")
    private LocalDateTime regDate;
}
