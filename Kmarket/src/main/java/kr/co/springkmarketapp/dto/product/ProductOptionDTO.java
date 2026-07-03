package kr.co.springkmarketapp.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductOptionDTO {
    private Integer optionNo;
    private Integer productNo;
    private Integer optionGroupNo;
    private String optionName;
    private String optionValue;
    private Integer sortOrder;
}
