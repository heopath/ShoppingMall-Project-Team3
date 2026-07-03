package kr.co.springkmarketapp.service.product;

import kr.co.springkmarketapp.dao.product.ProductOptionDAO;
import kr.co.springkmarketapp.dto.product.ProductOptionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductOptionService {

    private final ProductOptionDAO productOptionDAO;

    public int insertProductOption(ProductOptionDTO productOptionDTO) {
        return productOptionDAO.insertProductOption(productOptionDTO);
    }

    public ProductOptionDTO selectProductOption(Integer optionNo) {
        return productOptionDAO.selectProductOption(optionNo);
    }

    public List<ProductOptionDTO> selectProductOptionList() {
        return productOptionDAO.selectProductOptionList();
    }

    public int updateProductOption(ProductOptionDTO productOptionDTO) {
        return productOptionDAO.updateProductOption(productOptionDTO);
    }

    public int deleteProductOption(Integer optionNo) {
        return productOptionDAO.deleteProductOption(optionNo);
    }
}
