package kr.co.springkmarketapp.repository.product;

import kr.co.springkmarketapp.entity.product.ProductReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductReviewRepository extends JpaRepository<ProductReview, Long> {
}
