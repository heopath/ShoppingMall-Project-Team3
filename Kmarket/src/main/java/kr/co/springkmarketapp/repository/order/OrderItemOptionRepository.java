package kr.co.springkmarketapp.repository.order;

import kr.co.springkmarketapp.entity.order.OrderItemOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemOptionRepository extends JpaRepository<OrderItemOption, Long> {
}
