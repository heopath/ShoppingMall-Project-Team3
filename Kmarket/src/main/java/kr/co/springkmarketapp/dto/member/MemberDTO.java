package kr.co.springkmarketapp.dto.member;

import kr.co.springkmarketapp.entity.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
    private Integer memberNo;
    private String memberId;
    private String password;
    private String name;
    private String gender;
    private LocalDate birth;
    private String email;
    private String hp;
    private String role;
    private String grade;
    private Integer point;
    private String status;
    private String zip;
    private String addr1;
    private String addr2;
    private LocalDateTime lastLoginDate;
    private LocalDateTime leaveDate;
    private LocalDateTime regDate;

    public Member toEntity() {
        return Member.builder()
                .memberId(memberId)
                .password(password)
                .name(name)
                .gender(gender)
                .birth(birth)
                .email(email)
                .hp(hp)
                .role(role)
                .grade(grade)
                .point(point)
                .status(status)
                .zip(zip)
                .addr1(addr1)
                .addr2(addr2)
                .build();
    }
}
