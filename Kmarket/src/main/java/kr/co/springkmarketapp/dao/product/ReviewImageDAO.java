package kr.co.springkmarketapp.dao.product;

import kr.co.springkmarketapp.dto.product.ReviewImageDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewImageDAO {
    int insertReviewImage(ReviewImageDTO reviewImageDTO);
    ReviewImageDTO selectReviewImage(Long reviewImageNo);
    List<ReviewImageDTO> selectReviewImageList();
    int updateReviewImage(ReviewImageDTO reviewImageDTO);
    int deleteReviewImage(Long reviewImageNo);
}
