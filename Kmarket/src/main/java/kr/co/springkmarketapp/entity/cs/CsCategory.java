package kr.co.springkmarketapp.entity.cs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

@Entity
@Table(name = "cs_category")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CsCategory {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cs_cate_no")
    private Integer csCateNo;
    @Column(name = "parent_no")
    private Integer parentNo;
    @Column(name = "cate_name")
    private String cateName;
    @Column(name = "depth")
    private Integer depth;
    @Column(name = "sort_order")
    private Integer sortOrder;
    @Column(name = "use_yn")
    private String useYn;
}
