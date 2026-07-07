package kr.co.springkmarketapp.dao.product;

import kr.co.springkmarketapp.dto.common.PageRequestDTO;
import kr.co.springkmarketapp.dto.product.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

    // 카테고리 별 상품 list
    List<ProductListDTO> selectProductsByCategory(PageRequestDTO pageRequestDTO);
    int selectCountByCategory(PageRequestDTO pageRequestDTO);

    // 상품 상세 페이지 정보
    ProductViewDTO selectProductView(int productNo);
    List<ProductOptionDTO> selectProductOptions(int productNo);
    List<ProductImageDTO> selectDetailImages(int productNo);
    ProductNoticeDTO selectProductNotice(int productNo);
    List<ProductReviewDTO> selectProductReviewList(@Param("productNo") int productNo, @Param("pageRequestDTO") PageRequestDTO pageRequestDTO);
    int selectProductReviewCount(int productNo);


}