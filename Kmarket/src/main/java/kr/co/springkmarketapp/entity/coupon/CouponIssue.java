package kr.co.springkmarketapp.entity.coupon;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "coupon_issue")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CouponIssue {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "issue_no")
    private Long issueNo;
    @Column(name = "issue_code")
    private String issueCode;
    @Column(name = "coupon_no")
    private Integer couponNo;
    @Column(name = "member_no")
    private Integer memberNo;
    @Column(name = "order_no")
    private Long orderNo;
    @Column(name = "status")
    private String status;
    @Column(name = "used_date")
    private LocalDateTime usedDate;
    @Column(name = "issue_date")
    private LocalDateTime issueDate;
}
