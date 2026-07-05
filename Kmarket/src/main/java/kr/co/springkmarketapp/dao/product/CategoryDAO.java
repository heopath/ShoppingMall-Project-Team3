package kr.co.springkmarketapp.dao.product;

import kr.co.springkmarketapp.dto.product.CategoryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryDAO {
    int insertCategory(CategoryDTO categoryDTO);
    CategoryDTO selectCategory(Integer cateNo);
    List<CategoryDTO> selectCategoryList();
    int updateCategory(CategoryDTO categoryDTO);
    int deleteCategory(Integer cateNo);
}
