package kr.co.springkmarketapp.repository.order;

import kr.co.springkmarketapp.entity.order.CartOption;
import kr.co.springkmarketapp.entity.order.CartOptionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartOptionRepository extends JpaRepository<CartOption, CartOptionId> {
}
