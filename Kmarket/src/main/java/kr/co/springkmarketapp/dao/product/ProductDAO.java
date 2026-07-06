package kr.co.springkmarketapp.dao.product;

import kr.co.springkmarketapp.dto.product.ProductDTO;
import kr.co.springkmarketapp.dto.product.ProductMainDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductDAO {
    int insertProduct(ProductDTO productDTO);
    ProductDTO selectProduct(Integer productNo);
    List<ProductDTO> selectProductList();
    int updateProduct(ProductDTO productDTO);
    int deleteProduct(Integer productNo);

    // index 상품 리스트 mapper 요청
    List<ProductMainDTO> selectBestProducts();
    List<ProductMainDTO> selectHitProducts();
    List<ProductMainDTO> selectRecommendProducts();
    List<ProductMainDTO> selectNewProducts();
    List<ProductMainDTO> selectPopularProducts();
    List<ProductMainDTO> selectDiscountProducts();
}
