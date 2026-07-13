package kr.co.springkmarketapp.dao.coupon;

import kr.co.springkmarketapp.dto.coupon.CouponDTO;
import kr.co.springkmarketapp.dto.coupon.OrderCouponDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CouponDAO {

    /*
     * =========================
     * 관리자 쿠폰 관리
     * =========================
     */

    int insertCoupon(CouponDTO couponDTO);

    CouponDTO selectCoupon(Integer couponNo);

    List<CouponDTO> selectCouponList();

    List<CouponDTO> selectCouponListWithPaging(
            @Param("offset") int offset,
            @Param("limit") int limit
    );

    List<CouponDTO> selectCouponListBySearchWithPaging(
            @Param("searchType") String searchType,
            @Param("keyword") String keyword,
            @Param("offset") int offset,
            @Param("limit") int limit
    );

    int countCouponList();

    int countCouponListBySearch(
            @Param("searchType") String searchType,
            @Param("keyword") String keyword
    );

    List<CouponDTO> selectCouponListBySellerWithPaging(
            @Param("sellerNo") Integer sellerNo,
            @Param("offset") int offset,
            @Param("limit") int limit
    );

    List<CouponDTO> selectCouponListBySellerSearchWithPaging(
            @Param("sellerNo") Integer sellerNo,
            @Param("searchType") String searchType,
            @Param("keyword") String keyword,
            @Param("offset") int offset,
            @Param("limit") int limit
    );

    int countCouponListBySeller(@Param("sellerNo") Integer sellerNo);

    int countCouponListBySellerSearch(
            @Param("sellerNo") Integer sellerNo,
            @Param("searchType") String searchType,
            @Param("keyword") String keyword
    );

    int updateCoupon(CouponDTO couponDTO);

    int updateCouponStatus(
            @Param("couponNo") Integer couponNo,
            @Param("status") String status
    );

    int deleteCoupon(Integer couponNo);

    int deleteCoupons(
            @Param("couponNos") List<Integer> couponNos
    );


    /*
     * =========================
     * 주문 페이지 쿠폰
     * =========================
     */

    // 주문 페이지에서 회원이 사용할 수 있는 쿠폰 목록 조회
    List<OrderCouponDTO> selectAvailableCoupons(
            @Param("memberNo") Integer memberNo
    );

    // 주문 요청 시 선택한 쿠폰 1개 검증용 조회
    OrderCouponDTO selectAvailableCouponForOrder(
            @Param("memberNo") Integer memberNo,
            @Param("couponIssueNo") Long couponIssueNo
    );

    // 주문 완료 후 쿠폰 사용 처리
    int updateCouponUsed(
            @Param("memberNo") Integer memberNo,
            @Param("couponIssueNo") Long couponIssueNo,
            @Param("orderNo") Long orderNo
    );


    /*
     * =========================
     * 상품상세 쿠폰 모달
     * =========================
     */

    // 해당 판매자의 첫 이용 쿠폰 조회
    // coupon_code = FIRST_SELLER_{sellerNo}
    CouponDTO selectFirstSellerCoupon(
            @Param("sellerNo") Integer sellerNo
    );

    // 해당 판매자가 직접 등록한 쿠폰 목록 조회
    // FIRST_SELLER_%, AUTO_PRODUCT_% 제외
    List<CouponDTO> selectSellerRegisteredCoupons(
            @Param("sellerNo") Integer sellerNo
    );

    // 쿠폰 발급 전, 해당 couponNo가 발급 가능한 쿠폰인지 검증
    CouponDTO selectIssuableCoupon(
            @Param("couponNo") Integer couponNo
    );

    // 회원이 특정 쿠폰을 이미 발급받았는지 확인
    int countIssuedCoupon(
            @Param("memberNo") Integer memberNo,
            @Param("couponNo") Integer couponNo
    );

    // coupon_issue 발급
    int insertCouponIssue(
            @Param("issueCode") String issueCode,
            @Param("memberNo") Integer memberNo,
            @Param("couponNo") Integer couponNo
    );

    // 첫 이용 쿠폰 자동 생성 시 판매자 상점명 조회
    String selectSellerNameBySellerNo(
            @Param("sellerNo") Integer sellerNo
    );
}
