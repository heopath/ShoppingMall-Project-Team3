package kr.co.springkmarketapp.repository.member;

import kr.co.springkmarketapp.entity.member.MemberSocial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberSocialRepository extends JpaRepository<MemberSocial, Long> {
}
