package kr.co.springkmarketapp.repository.order;

import kr.co.springkmarketapp.entity.order.ClaimFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaimFileRepository extends JpaRepository<ClaimFile, Long> {
}
