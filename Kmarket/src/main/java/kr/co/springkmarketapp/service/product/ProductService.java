package kr.co.springkmarketapp.service.product;

import kr.co.springkmarketapp.dao.product.ProductDAO;
import kr.co.springkmarketapp.dto.common.PageRequestDTO;
import kr.co.springkmarketapp.dto.common.PageResponseDTO;
import kr.co.springkmarketapp.dto.product.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductDAO productDAO;
    private final RestClient.Builder builder;

    public int insertProduct(ProductDTO productDTO) {
        return productDAO.insertProduct(productDTO);
    }

    public ProductDTO selectProduct(Integer productNo) {
        return productDAO.selectProduct(productNo);
    }

    public List<ProductDTO> selectProductList() {
        return productDAO.selectProductList();
    }

    public int updateProduct(ProductDTO productDTO) {
        return productDAO.updateProduct(productDTO);
    }

    public int deleteProduct(Integer productNo) {
        return productDAO.deleteProduct(productNo);
    }

    // index product list mybatis 요청
    public List<ProductMainDTO> getBestProducts() {
        return productDAO.selectBestProducts();
    }
    public List<ProductMainDTO> getHitProducts() {
        return productDAO.selectHitProducts();
    }
    public List<ProductMainDTO> getRecommendProducts() {
        return productDAO.selectRecommendProducts();
    }
    public List<ProductMainDTO> getNewProducts() {
        return productDAO.selectNewProducts();
    }
    public List<ProductMainDTO> getPopularProducts() {
        return productDAO.selectPopularProducts();
    }
    public List<ProductMainDTO> getDiscountProducts() {
        return productDAO.selectDiscountProducts();
    }

    public PageResponseDTO<ProductListDTO> getProductsByCategory(PageRequestDTO pageRequestDTO) {
        List<ProductListDTO> dtoList = productDAO.selectProductsByCategory(pageRequestDTO);
        int total = productDAO.selectCountByCategory(pageRequestDTO);

        return new PageResponseDTO<>(
                dtoList,
                pageRequestDTO,
                total
        );
    }

    // 상품 상세 페이지
    public ProductViewDTO getProductView(int productNo) {
        return productDAO.selectProductView(productNo);
    }

    public List<ProductOptionDTO> getProductOptions(int productNo) {
        return productDAO.selectProductOptions(productNo);
    }

    public Map<Integer, List<ProductOptionDTO>> getProductOptionGroups(int productNo) {

        List<ProductOptionDTO> options =
                productDAO.selectProductOptions(productNo);

        return options.stream()
                .collect(Collectors.groupingBy(
                        ProductOptionDTO::getOptionGroupNo,
                        LinkedHashMap::new,
                        Collectors.toList()
                ));
    }

    public List<ProductImageDTO> getDetailImages(int productNo) {
        return productDAO.selectDetailImages(productNo);
    }

    public ProductNoticeDTO getProductNotice(int productNo) {
        return productDAO.selectProductNotice(productNo);
    }

    public PageResponseDTO<ProductReviewDTO> getProductReviews(int productNo, PageRequestDTO pageRequestDTO) {
        List<ProductReviewDTO> dtoList = productDAO.selectProductReviewList(productNo, pageRequestDTO);

        int total = productDAO.selectProductReviewCount(productNo);

        return PageResponseDTO.<ProductReviewDTO>builder()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total(total)
                .build();
    }


}
