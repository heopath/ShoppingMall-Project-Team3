package kr.co.springkmarketapp.service.product;

import kr.co.springkmarketapp.dao.product.ProductDAO;
import kr.co.springkmarketapp.dto.common.PageRequestDTO;
import kr.co.springkmarketapp.dto.common.PageResponseDTO;
import kr.co.springkmarketapp.dto.product.*;
import kr.co.springkmarketapp.util.FileStorageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClient;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductDAO productDAO;
    private final RestClient.Builder builder;
    private final FileStorageUtil fileStorageUtil;

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


    @Transactional
    public void registerProduct(ProductDTO dto,
                                MultipartFile thumb1, MultipartFile thumb2, MultipartFile thumb3,
                                List<MultipartFile> detailImages) {

        // 1. 상품 기본정보 등록
        productDAO.insertProduct(dto);
        int productNo = dto.getProductNo();

        // 2. 이미지 저장 (로컬 디스크) + DB insert
        List<ProductImageDTO> imageList = new ArrayList<>();
        saveImageIfPresent(thumb1, "THUMB1", productNo, imageList);
        saveImageIfPresent(thumb2, "THUMB2", productNo, imageList);
        saveImageIfPresent(thumb3, "THUMB3", productNo, imageList);

        if (detailImages != null) {
            int order = 0;
            for (MultipartFile file : detailImages) {
                if (file != null && !file.isEmpty()) {
                    String path = fileStorageUtil.saveFile(file, "product/detail");
                    imageList.add(ProductImageDTO.builder()
                            .productNo(productNo).imageType("DETAIL")
                            .imagePath(path).sortOrder(order++).build());
                }
            }
        }
        if (!imageList.isEmpty()) {
            productDAO.insertProductImages(imageList);
        }

        // 3. 옵션 등록 (없으면 자동 스킵 — 필수 아님)
        if (dto.getOptionNames() != null) {
            List<ProductOptionDTO> optionList = new ArrayList<>();
            for (int g = 0; g < dto.getOptionNames().size(); g++) {
                String name = dto.getOptionNames().get(g);
                String valuesRaw = dto.getOptionValues().get(g);
                if (name == null || name.isBlank() || valuesRaw == null || valuesRaw.isBlank()) continue;

                int order = 0;
                for (String v : valuesRaw.split(",")) {
                    if (v.isBlank()) continue;
                    optionList.add(ProductOptionDTO.builder()
                            .productNo(productNo).optionGroupNo(g + 1)
                            .optionName(name.trim()).optionValue(v.trim())
                            .sortOrder(order++).build());
                }
            }
            if (!optionList.isEmpty()) {
                productDAO.insertProductOptions(optionList);
            }
        }

        // 4. 제공고시 등록
        ProductNoticeDTO notice = ProductNoticeDTO.builder()
                .productNo(productNo)
                .productStatus(dto.getProductStatus())
                .taxType(dto.getTaxType())
                .receiptType(dto.getReceiptType())
                .businessType(dto.getBusinessType())
                .origin(dto.getOrigin())
                .build();
        productDAO.insertProductNotice(notice);
    }

    private void saveImageIfPresent(MultipartFile file, String type, int productNo, List<ProductImageDTO> list) {
        if (file != null && !file.isEmpty()) {
            String path = fileStorageUtil.saveFile(file, "product/" + type.toLowerCase());
            list.add(ProductImageDTO.builder()
                    .productNo(productNo).imageType(type)
                    .imagePath(path).sortOrder(0).build());
        }
    }
}