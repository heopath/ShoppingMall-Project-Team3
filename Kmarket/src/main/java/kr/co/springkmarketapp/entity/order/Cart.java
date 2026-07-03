package kr.co.springkmarketapp.entity.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "cart")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cart {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_no")
    private Integer cartNo;
    @Column(name = "member_no")
    private Integer memberNo;
    @Column(name = "product_no")
    private Integer productNo;
    @Column(name = "option_signature")
    private String optionSignature;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "reg_date")
    private LocalDateTime regDate;
}
