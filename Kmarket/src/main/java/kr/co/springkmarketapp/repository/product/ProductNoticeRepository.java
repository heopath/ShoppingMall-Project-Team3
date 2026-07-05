package kr.co.springkmarketapp.repository.product;

import kr.co.springkmarketapp.entity.product.ProductNotice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductNoticeRepository extends JpaRepository<ProductNotice, Integer> {
}
