package kr.co.springkmarketapp.service.my;

import jakarta.transaction.Transactional;
import kr.co.springkmarketapp.dao.my.MyDAO;
import kr.co.springkmarketapp.dto.common.PageRequestDTO;
import kr.co.springkmarketapp.dto.common.PageResponseDTO;
import kr.co.springkmarketapp.dto.coupon.CouponIssueDTO;
import kr.co.springkmarketapp.dto.cs.QnaDTO;
import kr.co.springkmarketapp.dto.member.MemberDTO;
import kr.co.springkmarketapp.dto.member.SellerProfileDTO;
import kr.co.springkmarketapp.dto.my.MemberPointDTO;
import kr.co.springkmarketapp.dto.order.OrderItemDTO;
import kr.co.springkmarketapp.dto.product.ProductReviewDTO;
import kr.co.springkmarketapp.dto.product.ReviewImageDTO;
import kr.co.springkmarketapp.util.FileStorageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MyService {

    private final MyDAO myDAO;
    private final FileStorageUtil fileStorageUtil;

    public List<OrderItemDTO> selectRecentOrderList(Long memberNo) {
        return myDAO.selectRecentOrderList(memberNo);
    }
    public List<OrderItemDTO> selectOrderDetail(Long orderNo) {
        return myDAO.selectOrderDetail(orderNo);
    }
    public SellerProfileDTO selectSellerDetail(Integer sellerNo){
        return myDAO.selectSellerDetail(sellerNo);
    }


    public Map<String, Integer> getMyPageSummary(Integer memberNo) {
        Map<String, Integer> summary = new HashMap<>();
        summary.put("orderCount", myDAO.selectOrderCount(memberNo));
        summary.put("couponCount", myDAO.selectCouponCount(memberNo));
        summary.put("point", myDAO.selectPoint(memberNo));
        summary.put("qnaCount", myDAO.selectQnaCount(memberNo));
        return summary;
    }

    // 문의하기 - 카테고리 조회
    public List<QnaDTO> selectQnaCategory(){
        return myDAO.selectQnaCategory();
    }

    // 문의하기 - 문의 작성
    @Transactional
    public void insertQna(QnaDTO dto){
        myDAO.insertQna(dto);
    }

    // 구매확정
    @Transactional
    public void confirmOrder(Long orderItemNo){
        myDAO.confirmOrder(orderItemNo);
    }

    // 상품평 작성 모달 정보 조회
    public ProductReviewDTO selectReviewInfo(Long orderItemNo){
        return myDAO.selectReviewInfo(orderItemNo);
    }

    // 상품평 등록
    @Transactional
    public void insertReview(ProductReviewDTO dto, MultipartFile[] images){

        // productNo 조회
        Integer productNo = myDAO.selectProductNo(dto.getOrderItemNo());
        dto.setProductNo(productNo);

        // product_review 저장
        myDAO.insertReview(dto);

        // 이미지 저장
        if(images != null){

            int sortOrder = 1;

            for(MultipartFile file : images){

                if(file.isEmpty()){
                    continue;
                }

                String imagePath =
                        fileStorageUtil.saveFile(file, "review");

                ReviewImageDTO imageDTO = ReviewImageDTO.builder()
                        .reviewNo(dto.getReviewNo())
                        .imagePath(imagePath)
                        .sortOrder(sortOrder++)
                        .build();

                myDAO.insertReviewImage(imageDTO);
            }
        }
    }

    // 메인 - 포인트 적립내역 조회
    public List<MemberPointDTO> selectPointList(Integer memberNo) {
        return myDAO.selectPointList(memberNo);
    }
    // 메뉴 - 포인트 적립내역 조회
    public List<MemberPointDTO> selectPointList(Integer memberNo, String startDate, String endDate, int start) {
        return myDAO.selectPointListWithPaging(memberNo, startDate, endDate, start, 10);
    }
    // 포인트 내역 개수 (기간 조건 포함)
    public Integer selectPointCount(Integer memberNo, String startDate, String endDate) {
        return myDAO.selectPointCount(memberNo, startDate, endDate);
    }

    // 메인 - 상품평 조회
    public List<ProductReviewDTO> selectReviewList(Integer memberNo) {
        return myDAO.selectReviewList(memberNo);
    }

    // 메뉴 - 상품평 전체조회
    public List<ProductReviewDTO> selectReviewList(Integer memberNo, int start) {
        return myDAO.selectReviewListWithPaging(memberNo, start, 10);
    }

    // 상품평 개수
    public Integer selectReviewCount(Integer memberNo){
        return myDAO.selectReviewCount(memberNo);
    }

    // 메인 - 문의내역 조회
    public List<QnaDTO> selectQnaList(Integer memberNo){
        return myDAO.selectMyQnaList(memberNo);
    }

    // 메뉴 - 문의내역 전체조회
    public List<QnaDTO> selectQnaList(Integer memberNo, int start, int size) {
        return myDAO.selectMyQnaListWithPaging(memberNo, start, size);
    }

    // 주문내역 갯수 조회
    public Integer selectOrderCount(Integer memberNo){
        return myDAO.selectOrderCount(memberNo);
    }

    // 기간조건 포함 주문내역 갯수 조회
    public Integer selectOrderCount(Integer memberNo,
                                    String startDate,
                                    String endDate){
        return myDAO.selectOrderCountWithDate(memberNo, startDate, endDate);
    }

    // 쿠폰 갯수 조회
    public Integer selectCouponCount(Integer memberNo){
        return myDAO.selectCouponCount(memberNo);
    }

    // 포인트 조회
    public Integer selectPoint(Integer memberNo){
        return myDAO.selectPoint(memberNo);
    }

    // 문의내역 갯수 조회
    public Integer selectQnaCount(Integer memberNo) {
        return myDAO.selectQnaCount(memberNo);
    }

    // 메뉴1 - 주문내역 전체조회
    public List<OrderItemDTO> selectOrderPage(Integer memberNo, int start){
        return myDAO.selectRecentOrderList(memberNo, start, 10);
    }
    // 전체조회(기간검색)
    public List<OrderItemDTO> selectOrderPage(Integer memberNo,
                                              String startDate,
                                              String endDate,
                                              int start,
                                              int size){
        return myDAO.selectOrderPage(memberNo, startDate, endDate, start, size);
    }

    // 쿠폰 내역 조회
    public List<CouponIssueDTO> selectCouponList(Integer memberNo, int start, int size){
        return myDAO.selectCouponList(memberNo, start, size);
    }

    // 회원정보 조회
    public MemberDTO selectMemberSetting(Integer memberNo){
        return myDAO.selectMemberSetting(memberNo);
    }

    // 회원정보 수정
    @Transactional
    public void updateMemberSetting(MemberDTO dto){
        myDAO.updateMemberSetting(dto);
    }

    // 회원 탈퇴
    @Transactional
    public void deleteMember(Integer memberNo) {
        myDAO.deleteMember(memberNo);
    }

    // 회원정보 중복 체크
    public int countMemberByValue(String type, String value, Integer memberNo) {
        return myDAO.countMemberByValue(type, value, memberNo);
    }

}