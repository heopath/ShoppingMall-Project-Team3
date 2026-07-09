package kr.co.springkmarketapp.dao.my;

import kr.co.springkmarketapp.dto.cs.QnaDTO;
import kr.co.springkmarketapp.dto.member.SellerProfileDTO;
import kr.co.springkmarketapp.dto.my.MemberPointDTO;
import kr.co.springkmarketapp.dto.order.OrderItemDTO;
import kr.co.springkmarketapp.dto.order.OrdersDTO;
import kr.co.springkmarketapp.dto.product.ProductReviewDTO;
import kr.co.springkmarketapp.dto.product.ReviewImageDTO;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MyDAO {

    List<OrderItemDTO> selectRecentOrderList(Long memberNo);
    List<OrderItemDTO> selectOrderDetail(Long orderNo);
    SellerProfileDTO selectSellerDetail(Integer sellerNo);

    // 문의하기
    List<QnaDTO> selectQnaCategory();
    void insertQna(QnaDTO dto);

    // 구매확정
    void confirmOrder(Long orderItemNo);

    // 상품평 작성 모달 정보 조회
    ProductReviewDTO selectReviewInfo(Long orderItemNo);

    // order_item에서 상품번호 조회
    Integer selectProductNo(Long orderItemNo);

    // product_review 등록
    void insertReview(ProductReviewDTO dto);

    // review_image 등록
    void insertReviewImage(ReviewImageDTO dto);

    // 포인트 적립 내역 조회
    List<MemberPointDTO> selectPointList(Integer memberNo);

    // 상품평 조회
    List<ProductReviewDTO> selectReviewList(Integer memberNo);

    // 문의내역 조회
    List<QnaDTO> selectMyQnaList(Integer memberNo);

    // 주문내역 갯수 조회
    Integer selectOrderCount(Integer memberNo);

    // 쿠폰 갯수 조회
    Integer selectCouponCount(Integer memberNo);

    // 포인트 조회
    Integer selectPoint(Integer memberNo);

    // 문의내역 갯수 조회
    Integer selectQnaCount(Integer memberNo);

    // 메뉴1 - 주문내역 전체조회
    List<OrderItemDTO> selectRecentOrderList(
            @Param("memberNo") Integer memberNo,
            @Param("start") Integer start,
            @Param("size") Integer size);
}