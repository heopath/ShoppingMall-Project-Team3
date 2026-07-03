package kr.co.springkmarketapp.entity.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "product_image")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductImage {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_no")
    private Integer imageNo;
    @Column(name = "product_no")
    private Integer productNo;
    @Column(name = "image_type")
    private String imageType;
    @Column(name = "image_path")
    private String imagePath;
    @Column(name = "sort_order")
    private Integer sortOrder;
    @Column(name = "reg_date")
    private LocalDateTime regDate;
}
