package kr.co.springkmarketapp.service.product;

import kr.co.springkmarketapp.dao.product.ProductDAO;
import kr.co.springkmarketapp.dto.product.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductDAO productDAO;

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
}
