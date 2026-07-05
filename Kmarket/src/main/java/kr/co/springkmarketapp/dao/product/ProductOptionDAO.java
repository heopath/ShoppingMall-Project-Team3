package kr.co.springkmarketapp.dao.product;

import kr.co.springkmarketapp.dto.product.ProductOptionDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductOptionDAO {
    int insertProductOption(ProductOptionDTO productOptionDTO);
    ProductOptionDTO selectProductOption(Integer optionNo);
    List<ProductOptionDTO> selectProductOptionList();
    int updateProductOption(ProductOptionDTO productOptionDTO);
    int deleteProductOption(Integer optionNo);
}
