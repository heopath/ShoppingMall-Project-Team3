package kr.co.springkmarketapp.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductMainDTO {

    private int productNo;
    private String productName;
    private String basicDesc;
    private String brand;

    private int price;
    private int discountRate;
    private int point;
    private int deliveryFee;

    private int viewCount;
    private int soldCount;

    private String imagePath;   // THUMB2: 메인 카드, 베스트상품 1위
    private String thumb1Path;  // THUMB1: 베스트상품 2~5위

    private Double avgRating;
    private int reviewCount;

    // 할인 적용 가격
    public int getDiscountPrice() {
        return price - (price * discountRate / 100);
    }
}
