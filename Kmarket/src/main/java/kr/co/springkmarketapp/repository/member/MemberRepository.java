package kr.co.springkmarketapp.repository.member;

import kr.co.springkmarketapp.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    int countByMemberId(String memberId);
    int countByEmail(String email);
    int countByHp(String hp);

}
