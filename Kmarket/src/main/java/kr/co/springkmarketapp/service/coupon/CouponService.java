package kr.co.springkmarketapp.service.coupon;

import kr.co.springkmarketapp.dao.coupon.CouponDAO;
import kr.co.springkmarketapp.dto.coupon.CouponDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponDAO couponDAO;

    public int insertCoupon(CouponDTO couponDTO) {
        return couponDAO.insertCoupon(couponDTO);
    }

    public CouponDTO selectCoupon(Integer couponNo) {
        return couponDAO.selectCoupon(couponNo);
    }

    public List<CouponDTO> selectCouponList() {
        return couponDAO.selectCouponList();
    }

    public List<CouponDTO> selectCouponList(int offset, int limit) {
        return couponDAO.selectCouponListWithPaging(offset, limit);
    }

    public List<CouponDTO> selectCouponList(String searchType, String keyword, int offset, int limit) {
        return couponDAO.selectCouponListBySearchWithPaging(searchType, keyword, offset, limit);
    }

    public int countCouponList() {
        return couponDAO.countCouponList();
    }

    public int countCouponList(String searchType, String keyword) {
        return couponDAO.countCouponListBySearch(searchType, keyword);
    }

    public int updateCoupon(CouponDTO couponDTO) {
        return couponDAO.updateCoupon(couponDTO);
    }

    public int updateCouponStatus(Integer couponNo, String status) {
        return couponDAO.updateCouponStatus(couponNo, status);
    }

    public int deleteCoupon(Integer couponNo) {
        return couponDAO.deleteCoupon(couponNo);
    }

    @Transactional
    public int deleteCoupons(List<Integer> couponNos) {
        return couponDAO.deleteCoupons(couponNos);
    }

    @Transactional(readOnly = true)
    public CouponDTO getSellerProductCouponPreview(Integer sellerNo) {

        if (sellerNo == null) {
            return null;
        }

        CouponDTO coupon = couponDAO.selectSellerProductCoupon(sellerNo);

        if (coupon != null) {
            return coupon;
        }

        // DB에는 아직 저장하지 않는 미리보기용 기본 쿠폰
        return CouponDTO.builder()
                .sellerNo(sellerNo)
                .couponType("개별상품 할인")
                .couponName("판매자 전용 10% 할인쿠폰")
                .benefitType("PERCENT")
                .benefitValue(10)
                .minOrderPrice(0)
                .maxDiscountPrice(0)
                .status("사용")
                .build();
    }

    @Transactional(readOnly = true)
    public boolean hasIssuedSellerProductCoupon(
            Integer memberNo,
            Integer sellerNo
    ) {
        if (memberNo == null || sellerNo == null) {
            return false;
        }

        return couponDAO.countIssuedSellerProductCoupon(memberNo, sellerNo) > 0;
    }

    private CouponDTO createAutoSellerProductCoupon(Integer sellerNo) {

        String sellerName = couponDAO.selectSellerNameBySellerNo(sellerNo);

        if (sellerName == null || sellerName.isBlank()) {
            sellerName = "판매자" + sellerNo;
        }

        CouponDTO coupon = CouponDTO.builder()
                .couponCode("AUTO_PRODUCT_" + sellerNo)
                .sellerNo(sellerNo)
                .couponType("개별상품 할인")
                .couponName(sellerName + " 10% 할인 쿠폰")
                .benefitType("PERCENT")
                .benefitValue(10)
                .minOrderPrice(0)
                .maxDiscountPrice(0)
                .issueLimit(null)
                .startDate(LocalDate.now())
                .endDate(LocalDate.of(2099, 12, 31))
                .caution("상품상세 자동발급 쿠폰")
                .status("사용")
                .build();

        try {
            couponDAO.insertCoupon(coupon);

            if (coupon.getCouponNo() == null) {
                throw new IllegalArgumentException("쿠폰 원본 생성에 실패했습니다.");
            }

            return coupon;

        } catch (DuplicateKeyException e) {
            CouponDTO existingCoupon =
                    couponDAO.selectSellerProductCoupon(sellerNo);

            if (existingCoupon == null) {
                throw e;
            }

            return existingCoupon;
        }
    }

    @Transactional
    public void issueSellerProductCoupon(
            Integer memberNo,
            Integer sellerNo
    ) {
        if (memberNo == null) {
            throw new IllegalArgumentException("로그인이 필요합니다.");
        }

        if (sellerNo == null) {
            throw new IllegalArgumentException("판매자 정보가 없습니다.");
        }

        // 1. 이미 같은 판매자 개별상품 할인 쿠폰을 발급받았는지 확인
        int issuedCount =
                couponDAO.countIssuedSellerProductCoupon(memberNo, sellerNo);

        if (issuedCount > 0) {
            throw new IllegalArgumentException("이미 발급받은 쿠폰입니다.");
        }

        // 2. 판매자 쿠폰 원본 조회
        CouponDTO coupon =
                couponDAO.selectSellerProductCoupon(sellerNo);

        // 3. 원본이 없으면 coupon 테이블에 자동 생성
        if (coupon == null) {
            coupon = createAutoSellerProductCoupon(sellerNo);
        }

        // 4. coupon_issue 발급
        String issueCode = "ISS" + System.currentTimeMillis();

        int result = couponDAO.insertCouponIssue(
                issueCode,
                memberNo,
                coupon.getCouponNo()
        );

        if (result != 1) {
            throw new IllegalArgumentException("쿠폰 발급에 실패했습니다.");
        }
    }
}
