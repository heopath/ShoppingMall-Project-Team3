package kr.co.springkmarketapp.dto.order;

import lombok.Data;

import java.util.List;

@Data
public class DirectOrderRequestDTO {

    private Integer productNo;
    private Integer quantity;
    private List<Integer> optionNos;
}