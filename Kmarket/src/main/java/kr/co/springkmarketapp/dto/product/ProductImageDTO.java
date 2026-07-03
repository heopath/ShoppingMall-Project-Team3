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
public class ProductImageDTO {
    private Integer imageNo;
    private Integer productNo;
    private String imageType;
    private String imagePath;
    private Integer sortOrder;
    private LocalDateTime regDate;
}
