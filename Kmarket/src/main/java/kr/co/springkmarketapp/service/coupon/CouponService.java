package kr.co.springkmarketapp.service.coupon;

import kr.co.springkmarketapp.dao.coupon.CouponDAO;
import kr.co.springkmarketapp.dto.coupon.CouponDTO;
import kr.co.springkmarketapp.dto.coupon.ProductCouponModalDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponDAO couponDAO;

    /*
     * =========================
     * 관리자 쿠폰 관리
     * =========================
     */

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


    /*
     * =========================
     * 상품상세 쿠폰 모달
     * =========================
     */

    @Transactional
    public List<ProductCouponModalDTO> getProductCouponModal(
            Integer memberNo,
            Integer sellerNo
    ) {
        if (sellerNo == null) {
            throw new IllegalArgumentException("판매자 정보가 없습니다.");
        }

        // 1. 첫 이용 쿠폰 조회
        CouponDTO firstCoupon =
                couponDAO.selectFirstSellerCoupon(sellerNo);

        // 2. 첫 이용 쿠폰이 없으면 자동 생성
        if (firstCoupon == null) {
            firstCoupon = createFirstSellerCoupon(sellerNo);
        }

        List<ProductCouponModalDTO> couponList = new ArrayList<>();

        // 3. 첫 이용 쿠폰은 항상 맨 위에 출력
        couponList.add(toModalDTO(firstCoupon, memberNo, true));

        // 4. 판매자가 직접 등록한 쿠폰 목록 조회
        List<CouponDTO> sellerCoupons =
                couponDAO.selectSellerRegisteredCoupons(sellerNo);

        for (CouponDTO coupon : sellerCoupons) {
            couponList.add(toModalDTO(coupon, memberNo, false));
        }

        return couponList;
    }

    private CouponDTO createFirstSellerCoupon(Integer sellerNo) {

        String sellerName =
                couponDAO.selectSellerNameBySellerNo(sellerNo);

        if (sellerName == null || sellerName.isBlank()) {
            sellerName = "판매자" + sellerNo;
        }

        CouponDTO coupon = CouponDTO.builder()
                .couponCode("FIRST_SELLER_" + sellerNo)
                .sellerNo(sellerNo)
                .couponType("개별상품 할인")
                .couponName(sellerName + " 첫 이용 10% 할인 쿠폰")
                .benefitType("PERCENT")
                .benefitValue(10)
                .minOrderPrice(0)
                .maxDiscountPrice(0)
                .issueLimit(null)
                .startDate(LocalDate.now())
                .endDate(LocalDate.of(2099, 12, 31))
                .caution("첫 이용 자동 쿠폰")
                .status("사용")
                .build();

        try {
            couponDAO.insertCoupon(coupon);

            if (coupon.getCouponNo() == null) {
                throw new IllegalArgumentException("첫 이용 쿠폰 생성에 실패했습니다.");
            }

            return coupon;

        } catch (DuplicateKeyException e) {
            /*
             * 동시에 여러 명이 같은 판매자 쿠폰 모달을 열면
             * FIRST_SELLER_{sellerNo} coupon_code 유니크 충돌이 날 수 있음.
             * 그 경우 이미 생성된 쿠폰을 다시 조회해서 사용.
             */
            CouponDTO existingCoupon =
                    couponDAO.selectFirstSellerCoupon(sellerNo);

            if (existingCoupon == null) {
                throw e;
            }

            return existingCoupon;
        }
    }

    private ProductCouponModalDTO toModalDTO(
            CouponDTO coupon,
            Integer memberNo,
            boolean firstCoupon
    ) {
        String couponType =
                normalizeCouponType(coupon.getCouponType());

        String benefitType =
                normalizeBenefitType(coupon.getBenefitType());

        boolean issued = false;

        if (memberNo != null && coupon.getCouponNo() != null) {
            issued = couponDAO.countIssuedCoupon(
                    memberNo,
                    coupon.getCouponNo()
            ) > 0;
        }

        return ProductCouponModalDTO.builder()
                .couponNo(coupon.getCouponNo())
                .couponCode(coupon.getCouponCode())
                .sellerNo(coupon.getSellerNo())
                .couponName(coupon.getCouponName())
                .couponType(couponType)
                .benefitType(benefitType)
                .benefitValue(n(coupon.getBenefitValue()))
                .minOrderPrice(n(coupon.getMinOrderPrice()))
                .maxDiscountPrice(n(coupon.getMaxDiscountPrice()))
                .discountText(createDiscountText(couponType, benefitType, n(coupon.getBenefitValue())))
                .conditionText(createConditionText(coupon))
                .firstCoupon(firstCoupon)
                .issued(issued)
                .build();
    }

    @Transactional
    public void issueProductCoupon(
            Integer memberNo,
            Integer couponNo
    ) {
        if (memberNo == null) {
            throw new IllegalArgumentException("로그인이 필요합니다.");
        }

        if (couponNo == null) {
            throw new IllegalArgumentException("쿠폰 정보가 없습니다.");
        }

        // 1. 발급 가능한 쿠폰인지 확인
        CouponDTO coupon =
                couponDAO.selectIssuableCoupon(couponNo);

        if (coupon == null) {
            throw new IllegalArgumentException("발급 가능한 쿠폰이 아닙니다.");
        }

        // 2. 같은 쿠폰 중복 발급 차단
        int issuedCount =
                couponDAO.countIssuedCoupon(memberNo, couponNo);

        if (issuedCount > 0) {
            throw new IllegalArgumentException("이미 발급받은 쿠폰입니다.");
        }

        // 3. 발급 코드 생성
        String issueCode = "ISS" + System.currentTimeMillis();

        // 4. coupon_issue INSERT
        int result = couponDAO.insertCouponIssue(
                issueCode,
                memberNo,
                couponNo
        );

        if (result != 1) {
            throw new IllegalArgumentException("쿠폰 발급에 실패했습니다.");
        }
    }

    /** 룰렛 후보 중 하나를 추첨하여 즉시 발급한다. */
    @Transactional
    public Map<String, Object> spinRoulette(Integer memberNo) {
        if (memberNo == null) {
            throw new IllegalArgumentException("로그인이 필요합니다.");
        }

        List<CouponDTO> candidates =
                couponDAO.selectIssuableRouletteCoupons(memberNo);

        if (candidates.isEmpty()) {
            throw new IllegalArgumentException("현재 새로 발급받을 수 있는 쿠폰이 없습니다.");
        }

        List<CouponDTO> displayedCandidates =
                candidates.subList(0, Math.min(candidates.size(), 6));

        CouponDTO winner = displayedCandidates.get(
                ThreadLocalRandom.current().nextInt(displayedCandidates.size())
        );

        issueProductCoupon(memberNo, winner.getCouponNo());

        String couponType = normalizeCouponType(winner.getCouponType());
        String benefitType = normalizeBenefitType(winner.getBenefitType());

        return Map.of(
                "couponNo", winner.getCouponNo(),
                "couponName", winner.getCouponName(),
                "discountText", createDiscountText(
                        couponType,
                        benefitType,
                        n(winner.getBenefitValue())
                )
        );
    }

    /** 현재 룰렛 판에 표시할 쿠폰(최대 6개)을 반환한다. */
    @Transactional(readOnly = true)
    public List<Map<String, Object>> getRoulettePrizes(Integer memberNo) {
        List<CouponDTO> candidates =
                couponDAO.selectIssuableRouletteCoupons(memberNo);

        return candidates.stream()
                .limit(6)
                .map(coupon -> Map.<String, Object>of(
                        "couponNo", coupon.getCouponNo(),
                        "couponName", coupon.getCouponName(),
                        "discountText", createDiscountText(
                                normalizeCouponType(coupon.getCouponType()),
                                normalizeBenefitType(coupon.getBenefitType()),
                                n(coupon.getBenefitValue())
                        )
                ))
                .toList();
    }


    /*
     * =========================
     * 내부 보조 메서드
     * =========================
     */

    private String normalizeCouponType(String couponType) {

        if (couponType == null) {
            return "";
        }

        String compact = couponType.replace(" ", "");

        if ("개별상품할인".equals(compact)) {
            return "PRODUCT";
        }

        if ("배송비무료".equals(compact)) {
            return "FREE_SHIPPING";
        }

        if ("주문상품할인".equals(compact)) {
            return "ORDER";
        }

        if ("회원가입축하".equals(compact)) {
            return "ORDER";
        }

        return compact;
    }

    private String normalizeBenefitType(String benefitType) {

        if (benefitType == null) {
            return "";
        }

        String compact = benefitType.replace(" ", "");
        String upper = compact.toUpperCase();

        if ("PERCENT".equals(upper)
                || "RATE".equals(upper)
                || "비율할인".equals(compact)) {
            return "PERCENT";
        }

        if ("AMOUNT".equals(upper)
                || "FIXED".equals(upper)
                || "금액할인".equals(compact)) {
            return "AMOUNT";
        }

        if ("FREE_SHIPPING".equals(upper)
                || "무료배송".equals(compact)) {
            return "FREE_SHIPPING";
        }

        return upper;
    }

    private String createDiscountText(
            String couponType,
            String benefitType,
            Integer benefitValue
    ) {
        int value = n(benefitValue);

        if ("FREE_SHIPPING".equals(couponType)
                || "FREE_SHIPPING".equals(benefitType)) {
            return "배송비 무료";
        }

        if ("PERCENT".equals(benefitType)) {
            return value + "%";
        }

        if ("AMOUNT".equals(benefitType)) {
            return NumberFormat
                    .getNumberInstance(Locale.KOREA)
                    .format(value) + "원";
        }

        return "할인";
    }

    private String createConditionText(CouponDTO coupon) {

        int minOrderPrice = n(coupon.getMinOrderPrice());
        int maxDiscountPrice = n(coupon.getMaxDiscountPrice());

        List<String> conditions = new ArrayList<>();

        if (minOrderPrice > 0) {
            conditions.add(
                    NumberFormat
                            .getNumberInstance(Locale.KOREA)
                            .format(minOrderPrice) + "원 이상 구매 시"
            );
        }

        if (maxDiscountPrice > 0) {
            conditions.add(
                    "최대 " +
                            NumberFormat
                                    .getNumberInstance(Locale.KOREA)
                                    .format(maxDiscountPrice) + "원 할인"
            );
        }

        if (conditions.isEmpty()) {
            return "조건 없이 사용 가능";
        }

        return String.join(" · ", conditions);
    }

    private int n(Integer value) {
        return value == null ? 0 : value;
    }
}
