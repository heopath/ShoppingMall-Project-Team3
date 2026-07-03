package kr.co.springkmarketapp.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private Integer cateNo;
    private Integer parentNo;
    private String cateName;
    private Integer depth;
    private Integer sortOrder;
    private String useYn;
}
