package kr.co.springkmarketapp.repository.my;

import kr.co.springkmarketapp.entity.my.MemberPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberPointRepository extends JpaRepository<MemberPoint, Integer> {
}
