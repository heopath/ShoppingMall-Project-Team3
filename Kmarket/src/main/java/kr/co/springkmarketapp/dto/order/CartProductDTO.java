package kr.co.springkmarketapp.dto.order;

import lombok.Data;

@Data
public class CartProductDTO {

    private Integer productNo;
    private Integer stock;
    private String status;
}