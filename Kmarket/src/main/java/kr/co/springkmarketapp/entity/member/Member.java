package kr.co.springkmarketapp.entity.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "member")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
public class Member {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_no")
    private Integer memberNo;
    @Column(name = "member_id")
    private String memberId;
    @Column(name = "password")
    private String password;
    @Column(name = "name")
    private String name;
    @Column(name = "gender")
    private String gender;
    @Column(name = "birth")
    private LocalDate birth;
    @Column(name = "email")
    private String email;
    @Column(name = "hp")
    private String hp;
    @Column(name = "role")
    private String role;
    @Column(name = "grade")
    private String grade;
    @Column(name = "point")
    private Integer point;
    @Column(name = "status")
    private String status;
    @Column(name = "zip")
    private String zip;
    @Column(name = "addr1")
    private String addr1;
    @Column(name = "addr2")
    private String addr2;
    @Column(name = "last_login_date")
    private LocalDateTime lastLoginDate;
    @Column(name = "leave_date")
    private LocalDateTime leaveDate;
    @Column(name = "reg_date")
    private LocalDateTime regDate;
}
