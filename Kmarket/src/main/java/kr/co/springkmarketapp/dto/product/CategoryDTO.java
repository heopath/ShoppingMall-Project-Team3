package kr.co.springkmarketapp.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    // 화면에서 1차 카테고리 아래 2차 카테고리 출력용
    private List<CategoryDTO> children = new ArrayList<>();

}
