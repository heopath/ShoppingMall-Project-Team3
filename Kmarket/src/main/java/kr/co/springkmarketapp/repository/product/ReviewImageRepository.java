package kr.co.springkmarketapp.repository.product;

import kr.co.springkmarketapp.entity.product.ReviewImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewImageRepository extends JpaRepository<ReviewImage, Long> {
}
