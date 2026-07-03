package kr.co.springkmarketapp.repository.order;

import kr.co.springkmarketapp.entity.order.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Integer> {
}
