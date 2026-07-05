package kr.co.springkmarketapp.entity.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

@Entity
@Table(name = "order_item_option")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemOption {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_option_no")
    private Long orderItemOptionNo;
    @Column(name = "order_item_no")
    private Long orderItemNo;
    @Column(name = "option_group_no")
    private Integer optionGroupNo;
    @Column(name = "option_name")
    private String optionName;
    @Column(name = "option_value")
    private String optionValue;
}
