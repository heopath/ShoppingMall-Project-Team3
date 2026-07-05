package kr.co.springkmarketapp.repository.product;

import kr.co.springkmarketapp.entity.product.ProductOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductOptionRepository extends JpaRepository<ProductOption, Integer> {
}
