package kr.co.springkmarketapp.service.product;

import kr.co.springkmarketapp.dao.product.ReviewImageDAO;
import kr.co.springkmarketapp.dto.product.ReviewImageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewImageService {

    private final ReviewImageDAO reviewImageDAO;

    public int insertReviewImage(ReviewImageDTO reviewImageDTO) {
        return reviewImageDAO.insertReviewImage(reviewImageDTO);
    }

    public ReviewImageDTO selectReviewImage(Long reviewImageNo) {
        return reviewImageDAO.selectReviewImage(reviewImageNo);
    }

    public List<ReviewImageDTO> selectReviewImageList() {
        return reviewImageDAO.selectReviewImageList();
    }

    public int updateReviewImage(ReviewImageDTO reviewImageDTO) {
        return reviewImageDAO.updateReviewImage(reviewImageDTO);
    }

    public int deleteReviewImage(Long reviewImageNo) {
        return reviewImageDAO.deleteReviewImage(reviewImageNo);
    }
}
