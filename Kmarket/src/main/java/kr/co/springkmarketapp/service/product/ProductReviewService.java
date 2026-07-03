package kr.co.springkmarketapp.service.product;

import kr.co.springkmarketapp.dao.product.ProductReviewDAO;
import kr.co.springkmarketapp.dto.product.ProductReviewDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductReviewService {

    private final ProductReviewDAO productReviewDAO;

    public int insertProductReview(ProductReviewDTO productReviewDTO) {
        return productReviewDAO.insertProductReview(productReviewDTO);
    }

    public ProductReviewDTO selectProductReview(Long reviewNo) {
        return productReviewDAO.selectProductReview(reviewNo);
    }

    public List<ProductReviewDTO> selectProductReviewList() {
        return productReviewDAO.selectProductReviewList();
    }

    public int updateProductReview(ProductReviewDTO productReviewDTO) {
        return productReviewDAO.updateProductReview(productReviewDTO);
    }

    public int deleteProductReview(Long reviewNo) {
        return productReviewDAO.deleteProductReview(reviewNo);
    }
}
