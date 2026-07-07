package kr.co.springkmarketapp.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductReviewDTO {

    // product_review 테이블
    private Long reviewNo;
    private Long orderItemNo;
    private Integer memberNo;
    private Integer productNo;
    private Integer rating;
    private String content;
    private LocalDateTime regDate;

    // member 테이블 조인 결과
    private String memberId;

    // review_image 대표 이미지 1장 조회 결과
    private String imagePath;


    // 화면 출력용: 아이디 마스킹
    public String getMaskedMemberId() {

        if (memberId == null || memberId.isBlank()) {
            return "-";
        }

        int visibleLength = Math.min(3, memberId.length());

        return memberId.substring(0, visibleLength)
                + "*".repeat(memberId.length() - visibleLength);
    }

    // 별점 CSS용 퍼센트
    public int getRatingPercent() {
        return rating == null ? 0 : rating * 20;
    }


}