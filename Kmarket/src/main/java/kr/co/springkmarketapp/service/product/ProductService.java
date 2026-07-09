package kr.co.springkmarketapp.service.product;

import kr.co.springkmarketapp.dao.product.ProductDAO;
import kr.co.springkmarketapp.dto.common.PageRequestDTO;
import kr.co.springkmarketapp.dto.common.PageResponseDTO;
import kr.co.springkmarketapp.dto.product.*;
import kr.co.springkmarketapp.util.FileStorageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClient;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductDAO productDAO;
    private final RestClient.Builder builder;
    private final FileStorageUtil fileStorageUtil;

    @Value("${file.upload-dir}")
    private String uploadDir;

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

        public PageResponseDTO<ProductListDTO> getProductsBySearch(PageRequestDTO pageRequestDTO) {

        List<ProductListDTO> dtoList =
                productDAO.selectProductsBySearch(pageRequestDTO);

        int total =
                productDAO.selectCountBySearch(pageRequestDTO);

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
                    String path = saveProductImageFile(file, "detail");

                    imageList.add(ProductImageDTO.builder()
                            .productNo(productNo)
                            .imageType("DETAIL")
                            .imagePath(path)
                            .sortOrder(order++)
                            .build());
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

    // 관리자 상품 조회 서비스
    public Map<String, Object> getAdminProductList(int page, String searchType, String keyword) {
        int size = 10;
        int offset = (page - 1) * size;

        List<ProductDTO> list = productDAO.selectAdminProductList(offset, size, searchType, keyword);
        int total = productDAO.selectAdminProductCount(searchType, keyword);
        int totalPages = (int) Math.ceil((double) total / size);

        Map<String, Object> result = new HashMap<>();
        result.put("list", list);
        result.put("totalPages", totalPages);
        return result;
    }

    public List<ProductImageDTO> getAllImages(int productNo) {
        return productDAO.selectAllImagesByProduct(productNo);
    }

    // 관리자 상품 수정
    @Transactional
    public void modifyProduct(ProductDTO dto,
                              MultipartFile thumb1, MultipartFile thumb2, MultipartFile thumb3,
                              List<MultipartFile> detailImages) {

        int productNo = dto.getProductNo();

        // 1. 기본정보 수정
        productDAO.updateProduct(dto);

        // 2. 이미지 - 새 파일 있으면 기존 타입 것만 지우고 재삽입
        replaceImageIfPresent(thumb1, "THUMB1", productNo);
        replaceImageIfPresent(thumb2, "THUMB2", productNo);
        replaceImageIfPresent(thumb3, "THUMB3", productNo);

        if (detailImages != null && !detailImages.isEmpty() && !detailImages.get(0).isEmpty()) {
            // DETAIL 타입만 삭제 (THUMB1~3은 보존)
            productDAO.deleteProductImageByType(productNo, "DETAIL");

            List<ProductImageDTO> imageList = new ArrayList<>();
            int order = 0;
            for (MultipartFile file : detailImages) {
                if (file != null && !file.isEmpty()) {
                    String path = saveProductImageFile(file, "detail");
                    imageList.add(ProductImageDTO.builder()
                            .productNo(productNo).imageType("DETAIL")
                            .imagePath(path).sortOrder(order++).build());
                }
            }
            if (!imageList.isEmpty()) productDAO.insertProductImages(imageList);
        }

        // 3. 옵션 - 전체 삭제 후 재삽입 (등록 로직 재사용)
        productDAO.deleteProductOptions(productNo);
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
            if (!optionList.isEmpty()) productDAO.insertProductOptions(optionList);
        }

        // 4. 제공고시 수정
        ProductNoticeDTO notice = ProductNoticeDTO.builder()
                .productNo(productNo)
                .productStatus(dto.getProductStatus())
                .taxType(dto.getTaxType())
                .receiptType(dto.getReceiptType())
                .businessType(dto.getBusinessType())
                .origin(dto.getOrigin())
                .build();
        productDAO.updateProductNotice(notice);
    }   // ← modifyProduct 메서드를 닫는 중괄호 (이게 빠져있었음)

    private void saveImageIfPresent(MultipartFile file, String type, int productNo, List<ProductImageDTO> list) {
        if (file != null && !file.isEmpty()) {
            String path = saveProductImageFile(file, type.toLowerCase());

            list.add(ProductImageDTO.builder()
                    .productNo(productNo)
                    .imageType(type)
                    .imagePath(path)
                    .sortOrder(0)
                    .build());
        }
    }

    private void replaceImageIfPresent(MultipartFile file, String type, int productNo) {
        if (file != null && !file.isEmpty()) {
            // 해당 타입의 기존 이미지만 삭제 (다른 타입은 보존)
            productDAO.deleteProductImageByType(productNo, type);

            String path = saveProductImageFile(file, type.toLowerCase());
            productDAO.insertProductImages(List.of(
                    ProductImageDTO.builder()
                            .productNo(productNo)
                            .imageType(type)
                            .imagePath(path)
                            .sortOrder(0)
                            .build()
            ));
        }
    }
    private String saveProductImageFile(MultipartFile file, String folderName) {
        if (file == null || file.isEmpty()) {
            return null;
        }

        // 반드시 절대경로로 만들어야 Tomcat이 multipart.location 기준으로 재해석하지 않음
        String projectRoot = System.getProperty("user.dir");
        File folder = new File(projectRoot, uploadDir + "/product/" + folderName).getAbsoluteFile();

        if (!folder.exists()) {
            folder.mkdirs();
        }

        String originalFilename = file.getOriginalFilename();
        String savedFileName = UUID.randomUUID() + "_" + originalFilename;

        try {
            // dest 자체를 절대경로 File로 명시
            File dest = new File(folder, savedFileName).getAbsoluteFile();
            file.transferTo(dest);
        } catch (IOException e) {
            log.error("상품 이미지 저장 실패: ", e);
            throw new RuntimeException("상품 이미지 저장 중 오류가 발생했습니다.");
        }

        return "/uploads/product/" + folderName + "/" + savedFileName;
    }

    // 관리자 상품 삭제(→ 판매중지)
    public void stopSaleProduct(int productNo) {
        productDAO.updateProductStatus(productNo, "판매중지");
    }

    public void stopSaleProducts(List<Integer> productNos) {
        productDAO.updateProductStatusMulti(productNos, "판매중지");
    }

}