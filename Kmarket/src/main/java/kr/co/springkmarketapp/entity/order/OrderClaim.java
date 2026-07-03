package kr.co.springkmarketapp.entity.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "order_claim")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderClaim {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "claim_no")
    private Long claimNo;
    @Column(name = "order_item_no")
    private Long orderItemNo;
    @Column(name = "member_no")
    private Integer memberNo;
    @Column(name = "claim_type")
    private String claimType;
    @Column(name = "claim_reason")
    private String claimReason;
    @Column(name = "detail_reason")
    private String detailReason;
    @Column(name = "status")
    private String status;
    @Column(name = "request_date")
    private LocalDateTime requestDate;
    @Column(name = "process_date")
    private LocalDateTime processDate;
}
