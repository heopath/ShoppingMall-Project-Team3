package kr.co.springkmarketapp.entity.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

@Entity
@Table(name = "product_option")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductOption {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "option_no")
    private Integer optionNo;
    @Column(name = "product_no")
    private Integer productNo;
    @Column(name = "option_group_no")
    private Integer optionGroupNo;
    @Column(name = "option_name")
    private String optionName;
    @Column(name = "option_value")
    private String optionValue;
    @Column(name = "sort_order")
    private Integer sortOrder;
}
