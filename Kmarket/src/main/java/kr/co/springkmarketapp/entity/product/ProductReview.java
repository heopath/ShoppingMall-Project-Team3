package kr.co.springkmarketapp.entity.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "product_review")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductReview {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_no")
    private Long reviewNo;
    @Column(name = "order_item_no")
    private Long orderItemNo;
    @Column(name = "member_no")
    private Integer memberNo;
    @Column(name = "product_no")
    private Integer productNo;
    @Column(name = "rating")
    private Integer rating;
    @Column(name = "content")
    private String content;
    @Column(name = "reg_date")
    private LocalDateTime regDate;
}
