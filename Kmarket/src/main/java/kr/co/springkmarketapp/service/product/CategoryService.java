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
}
