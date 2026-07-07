package kr.co.springkmarketapp.dto.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductViewDTO {

    // 상품 기본
    private int productNo;
    private int sellerNo;
    private int cateNo;

    private String productName;
    private String basicDesc;
    private String brand;

    private int price;
    private int discountRate;
    private int point;
    private int stock;
    private int deliveryFee;
    private String status;

    // 상품 고시
    private String origin;

    // 대표 이미지
    private String imagePath;

    // 판매자
    private String sellerName;

    // 상품 리뷰
    private Double avgRating;
    private int reviewCount;

    // 할인 적용가
    public int getSalePrice() {
        return price - (price * discountRate / 100);
    }

    // 별점 CSS 퍼센트
    public double getRatingPercent() {
        return avgRating == null ? 0 : avgRating * 20;
    }
}
