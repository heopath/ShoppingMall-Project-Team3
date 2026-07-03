package kr.co.springkmarketapp.repository.member;

import kr.co.springkmarketapp.entity.member.SellerProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerProfileRepository extends JpaRepository<SellerProfile, Integer> {
}
