package kr.co.springkmarketapp.service.product;

import kr.co.springkmarketapp.dao.product.ProductImageDAO;
import kr.co.springkmarketapp.dto.product.ProductImageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductImageService {

    private final ProductImageDAO productImageDAO;

    public int insertProductImage(ProductImageDTO productImageDTO) {
        return productImageDAO.insertProductImage(productImageDTO);
    }

    public ProductImageDTO selectProductImage(Integer imageNo) {
        return productImageDAO.selectProductImage(imageNo);
    }

    public List<ProductImageDTO> selectProductImageList() {
        return productImageDAO.selectProductImageList();
    }

    public int updateProductImage(ProductImageDTO productImageDTO) {
        return productImageDAO.updateProductImage(productImageDTO);
    }

    public int deleteProductImage(Integer imageNo) {
        return productImageDAO.deleteProductImage(imageNo);
    }
}
