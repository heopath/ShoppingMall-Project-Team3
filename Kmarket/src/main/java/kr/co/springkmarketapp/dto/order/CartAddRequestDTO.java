package kr.co.springkmarketapp.dto.order;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CartAddRequestDTO {

    private Integer productNo;

    private List<Integer> optionNos;

    private Integer quantity;
}