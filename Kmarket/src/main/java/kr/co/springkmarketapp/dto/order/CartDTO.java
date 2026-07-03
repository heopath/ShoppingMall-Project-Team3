package kr.co.springkmarketapp.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {
    private Integer cartNo;
    private Integer memberNo;
    private Integer productNo;
    private String optionSignature;
    private Integer quantity;
    private LocalDateTime regDate;
}
