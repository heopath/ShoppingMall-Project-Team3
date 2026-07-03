package kr.co.springkmarketapp.entity.policy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "policy")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Policy {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "policy_no")
    private Integer policyNo;
    @Column(name = "policy_type")
    private String policyType;
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "required_yn")
    private String requiredYn;
    @Column(name = "use_yn")
    private String useYn;
    @Column(name = "update_date")
    private LocalDateTime updateDate;
}
