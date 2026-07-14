package kr.co.springkmarketapp.dto.admin;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryOrderDTO {
    private int cateNo;
    private int cateOrder;
    private Integer parentNo; // 1차는 null

}