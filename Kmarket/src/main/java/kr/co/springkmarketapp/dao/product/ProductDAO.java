package kr.co.springkmarketapp.dao.product;

import kr.co.springkmarketapp.dto.common.PageRequestDTO;
import kr.co.springkmarketapp.dto.product.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductDAO {
    // 상품 등록 (기존에 있다고 하셨음 - 시그니처 확인용)
    int insertProduct(ProductDTO productDTO);
    // 이미지 일괄 등록
    void insertProductImages(List<ProductImageDTO> imageList);
    // 옵션 일괄 등록
    void insertProductOptions(List<ProductOptionDTO> optionList);
    // 제공고시 등록
    void insertProductNotice(ProductNoticeDTO noticeDTO);
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

    // 상품 검색
    List<ProductListDTO> selectProductsBySearch(PageRequestDTO pageRequestDTO);
    int selectCountBySearch(PageRequestDTO pageRequestDTO);

    // 상품 상세 페이지 정보
    ProductViewDTO selectProductView(int productNo);
    List<ProductOptionDTO> selectProductOptions(int productNo);
    List<ProductImageDTO> selectDetailImages(int productNo);
    ProductNoticeDTO selectProductNotice(int productNo);
    List<ProductReviewDTO> selectProductReviewList(@Param("productNo") int productNo, @Param("pageRequestDTO") PageRequestDTO pageRequestDTO);
    int selectProductReviewCount(int productNo);

    // 관리자 상품 상세 보기
    List<ProductImageDTO> selectAllImagesByProduct(int productNo);

    // 관리자 상품 조회
    List<ProductDTO> selectAdminProductList(@Param("offset") int offset, @Param("size") int size,
                                            @Param("searchType") String searchType, @Param("keyword") String keyword);
    int selectAdminProductCount(@Param("searchType") String searchType, @Param("keyword") String keyword);

    // 관리자 상품 수정
    void updateProductNotice(ProductNoticeDTO noticeDTO);


    // 관리자 상품 삭제
    void deleteProductImageByType(@Param("productNo") int productNo, @Param("imageType") String imageType);
    void updateProductStatus(@Param("productNo") int productNo, @Param("status") String status);
    void updateProductStatusMulti(@Param("list") List<Integer> productNos, @Param("status") String status);
    void deleteProductOptions(int productNo);
}