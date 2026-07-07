package kr.co.springkmarketapp.dto.order;

import lombok.Data;

import java.util.List;

@Data
public class CartDeleteRequestDTO {

    private List<Integer> cartNos;
}