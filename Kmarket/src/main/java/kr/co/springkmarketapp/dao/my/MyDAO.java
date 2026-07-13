package kr.co.springkmarketapp.dao.my;

import kr.co.springkmarketapp.dto.coupon.CouponIssueDTO;
import kr.co.springkmarketapp.dto.cs.QnaDTO;
import kr.co.springkmarketapp.dto.member.MemberDTO;
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

    // 메인 - 포인트 적립 내역 조회
    List<MemberPointDTO> selectPointList(Integer memberNo);

    // 메뉴 - 포인트 적립 내역 조회
    List<MemberPointDTO> selectPointListWithPaging(
            @Param("memberNo") Integer memberNo,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate,
            @Param("start") int start,
            @Param("size") int size
    );
    // 메뉴 - 포인트 적립 내역 갯수
    Integer selectPointCount(@Param("memberNo") Integer memberNo,
                             @Param("startDate") String startDate,
                             @Param("endDate") String endDate);

    // 메인 - 상품평 조회
    List<ProductReviewDTO> selectReviewList(Integer memberNo);
    // 메뉴 - 상품평 조회
    List<ProductReviewDTO> selectReviewListWithPaging(
            @Param("memberNo") Integer memberNo,
            @Param("start") int start,
            @Param("size") int size
    );
    // 메뉴 -  상품평 개수
    Integer selectReviewCount(Integer memberNo);

    // 메인 - 문의내역 조회
    List<QnaDTO> selectMyQnaList(Integer memberNo);
    // 메뉴 - 문의내역 조회
    List<QnaDTO> selectMyQnaListWithPaging(
            @Param("memberNo") Integer memberNo,
            @Param("start") int start,
            @Param("size") int size
    );
    // 주문내역 갯수 조회
    Integer selectOrderCount(@Param("memberNo") Integer memberNo);
    // 기간 검색용
    Integer selectOrderCountWithDate(@Param("memberNo") Integer memberNo,
                                     @Param("startDate") String startDate,
                                     @Param("endDate") String endDate);

    // 쿠폰 갯수 조회
    Integer selectCouponCount(Integer memberNo);

    // 포인트 조회
    Integer selectPoint(Integer memberNo);

    // 문의내역 갯수 조회
    Integer selectQnaCount(Integer memberNo);

    // 메뉴 - 주문내역 전체조회
    List<OrderItemDTO> selectRecentOrderList(
            @Param("memberNo") Integer memberNo,
            @Param("start") Integer start,
            @Param("size") Integer size);

    List<OrderItemDTO> selectOrderPage(
            @Param("memberNo") Integer memberNo,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate,
            @Param("start") Integer start,
            @Param("size") Integer size);

    // 쿠폰 내역 조회
    List<CouponIssueDTO> selectCouponList(
            @Param("memberNo") Integer memberNo,
            @Param("start") int start,
            @Param("size") int size
    );

    // 회원정보 조회
    MemberDTO selectMemberSetting(Integer memberNo);

    // 회원정보 수정
    void updateMemberSetting(MemberDTO dto);
    
    // 회원 정보 중복 체크
    int countMemberByValue(@Param("type") String type,
                           @Param("value") String value,
                           @Param("memberNo") Integer memberNo);

    // 회원 탈퇴
    void deleteMember(Integer memberNo);
}