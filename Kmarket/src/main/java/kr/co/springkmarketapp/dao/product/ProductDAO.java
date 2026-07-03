package kr.co.springkmarketapp.dao.product;

import kr.co.springkmarketapp.dto.product.ProductDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductDAO {
    int insertProduct(ProductDTO productDTO);
    ProductDTO selectProduct(Integer productNo);
    List<ProductDTO> selectProductList();
    int updateProduct(ProductDTO productDTO);
    int deleteProduct(Integer productNo);
}
