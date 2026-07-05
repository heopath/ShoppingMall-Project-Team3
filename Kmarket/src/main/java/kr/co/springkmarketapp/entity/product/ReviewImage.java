package kr.co.springkmarketapp.entity.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

@Entity
@Table(name = "review_image")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewImage {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_image_no")
    private Long reviewImageNo;
    @Column(name = "review_no")
    private Long reviewNo;
    @Column(name = "image_path")
    private String imagePath;
    @Column(name = "sort_order")
    private Integer sortOrder;
}
