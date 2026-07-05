package kr.co.springkmarketapp.entity.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

@Entity
@Table(name = "cart_option")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(CartOptionId.class)
public class CartOption {


    @Id
    @Column(name = "cart_no")
    private Integer cartNo;
    @Id
    @Column(name = "option_no")
    private Integer optionNo;
}
