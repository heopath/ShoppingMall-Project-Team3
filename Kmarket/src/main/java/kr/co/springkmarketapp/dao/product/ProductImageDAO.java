package kr.co.springkmarketapp.dao.product;

import kr.co.springkmarketapp.dto.product.ProductImageDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductImageDAO {
    int insertProductImage(ProductImageDTO productImageDTO);
    ProductImageDTO selectProductImage(Integer imageNo);
    List<ProductImageDTO> selectProductImageList();
    int updateProductImage(ProductImageDTO productImageDTO);
    int deleteProductImage(Integer imageNo);
}
