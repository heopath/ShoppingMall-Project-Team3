package kr.co.springkmarketapp.repository.product;

import kr.co.springkmarketapp.entity.product.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    // 1차 대분류 목록 조회 (parent_no가 null이고 depth가 1인 항목을 순서대로)
    List<Category> findByParentIsNullAndDepthAndUseYnOrderBySortOrderAsc(Integer depth, String useYn);

    // 특정 1차 카테고리(부모)에 속한 2차 중분류 목록 조회 (AJAX 연동용)
    List<Category> findByParentCateNoAndUseYnOrderBySortOrderAsc(Integer parentNo, String useYn);
}