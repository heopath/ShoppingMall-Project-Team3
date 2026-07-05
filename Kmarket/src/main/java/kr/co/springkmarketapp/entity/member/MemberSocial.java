package kr.co.springkmarketapp.entity.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "member_social")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberSocial {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "social_no")
    private Long socialNo;
    @Column(name = "member_no")
    private Integer memberNo;
    @Column(name = "provider")
    private String provider;
    @Column(name = "provider_user_id")
    private String providerUserId;
    @Column(name = "provider_email")
    private String providerEmail;
    @Column(name = "reg_date")
    private LocalDateTime regDate;
}
