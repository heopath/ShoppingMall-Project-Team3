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
    private Long reviewNo;
    private Long orderItemNo;
    private Integer memberNo;
    private Integer productNo;
    private Integer rating;
    private String content;
    private LocalDateTime regDate;
}
