package kr.co.springkmarketapp.dao.product;

import kr.co.springkmarketapp.dto.product.ProductReviewDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductReviewDAO {
    int insertProductReview(ProductReviewDTO productReviewDTO);
    ProductReviewDTO selectProductReview(Long reviewNo);
    List<ProductReviewDTO> selectProductReviewList();
    int updateProductReview(ProductReviewDTO productReviewDTO);
    int deleteProductReview(Long reviewNo);
}
