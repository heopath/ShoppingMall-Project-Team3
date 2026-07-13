package kr.co.springkmarketapp.dto.product;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductListDTO {

    private int productNo;
    private String productName;
    private String basicDesc;

    private int price;
    private int discountRate;
    private int stock;
    private int deliveryFee;

    private String imagePath;

    private String sellerName;

    private double avgRating;
    private int reviewCount;

    // 판매자 등급 처리를 위한 추가 필드
    private Double sellerAvgRating;
    private int sellerReviewCount;
    private int sellerSoldCount;

    private String sellerBadgeCode;

    // 할인 적용가
    public int getSalePrice() {
        return price - (price * discountRate / 100);
    }

    // 별점 CSS 비율용
    public double getRatingPercent() {
        return avgRating * 20;
    }

    // 뱃지명 처리
    public String getSellerBadgeName() {

        return switch (sellerBadgeCode) {
            case "SATISFACTION" -> "고객만족우수";
            case "BEST" -> "베스트판매자";
            case "EXCELLENT" -> "우수판매자";
            default -> "일반판매자";
        };
    }
}