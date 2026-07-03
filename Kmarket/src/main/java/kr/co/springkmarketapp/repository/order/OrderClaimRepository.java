package kr.co.springkmarketapp.repository.order;

import kr.co.springkmarketapp.entity.order.OrderClaim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderClaimRepository extends JpaRepository<OrderClaim, Long> {
}
