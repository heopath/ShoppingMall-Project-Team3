package kr.co.springkmarketapp.entity.order;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CartOptionId implements Serializable {

    private Integer cartNo;
    private Integer optionNo;
}
