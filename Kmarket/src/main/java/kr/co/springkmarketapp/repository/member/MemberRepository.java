package kr.co.springkmarketapp.repository.member;

import kr.co.springkmarketapp.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    int countByMemberId(String memberId);
    int countByEmail(String email);
    int countByHp(String hp);

    // 로그인 시 사용자가 입력한 아이디(memberId)로 회원 조회
    Optional<Member> findByMemberId(String memberId);
}
