package kr.co.springkmarketapp.service.product;

import kr.co.springkmarketapp.dao.product.CategoryDAO;
import kr.co.springkmarketapp.dto.product.CategoryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryDAO categoryDAO;

    public int insertCategory(CategoryDTO categoryDTO) {
        return categoryDAO.insertCategory(categoryDTO);
    }

    public CategoryDTO selectCategory(Integer cateNo) {
        return categoryDAO.selectCategory(cateNo);
    }

    public List<CategoryDTO> selectCategoryList() {
        return categoryDAO.selectCategoryList();
    }

    public int updateCategory(CategoryDTO categoryDTO) {
        return categoryDAO.updateCategory(categoryDTO);
    }

    public int deleteCategory(Integer cateNo) {
        return categoryDAO.deleteCategory(cateNo);
    }

    // 카테고리 목록 리스트 출력
    public List<CategoryDTO> getCategoryTree() {

        List<CategoryDTO> mainCategories = categoryDAO.selectMainCategories();
        List<CategoryDTO> subCategories = categoryDAO.selectSubCategories();

        for (CategoryDTO main : mainCategories) {
            for (CategoryDTO sub : subCategories) {

                if (sub.getParentNo() != null && sub.getParentNo().equals(main.getCateNo())) {

                    main.getChildren().add(sub);
                }
            }
        }

        return mainCategories;
    }
}
