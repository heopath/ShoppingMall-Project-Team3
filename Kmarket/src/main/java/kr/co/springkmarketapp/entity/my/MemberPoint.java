package kr.co.springkmarketapp.entity.my;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "member_point")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberPoint {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "point_no")
    private Integer pointNo;
    @Column(name = "member_no")
    private Integer memberNo;
    @Column(name = "order_no")
    private Long orderNo;
    @Column(name = "point_type")
    private String pointType;
    @Column(name = "point_value")
    private Integer pointValue;
    @Column(name = "balance_point")
    private Integer balancePoint;
    @Column(name = "reason")
    private String reason;
    @Column(name = "expire_date")
    private LocalDate expireDate;
    @Column(name = "reg_date")
    private LocalDateTime regDate;
}
