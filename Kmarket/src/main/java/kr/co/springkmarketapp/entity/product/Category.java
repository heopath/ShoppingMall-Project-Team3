package kr.co.springkmarketapp.entity.product;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cate_no")
    private Integer cateNo;

    @Column(name = "cate_name", nullable = false, length = 100)
    private String cateName;

    @Column(name = "depth", nullable = false)
    private Integer depth; // 1: 대분류, 2: 중분류

    @Column(name = "sort_order", nullable = false)
    private Integer sortOrder;

    @Column(name = "use_yn", nullable = false, length = 1)
    private String useYn; // 'Y' 또는 'N'

    // --- 계층형 외래키(FK) 셀프 참조 구조 ---

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_no")
    private Category parent; // 부모 카테고리 (대분류일 경우 null)

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    @OrderBy("sortOrder ASC") // 정렬 순서 보장
    @Builder.Default
    private List<Category> children = new ArrayList<>(); // 자식 중분류 리스트
}