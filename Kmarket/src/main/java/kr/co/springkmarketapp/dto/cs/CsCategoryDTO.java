package kr.co.springkmarketapp.dto.cs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CsCategoryDTO {
    private Integer csCateNo;
    private Integer parentNo;
    private String cateName;
    private Integer depth;
    private Integer sortOrder;
    private String useYn;
}
