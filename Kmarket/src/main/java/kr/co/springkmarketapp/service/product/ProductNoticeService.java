package kr.co.springkmarketapp.service.product;

import kr.co.springkmarketapp.dao.product.ProductNoticeDAO;
import kr.co.springkmarketapp.dto.product.ProductNoticeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductNoticeService {

    private final ProductNoticeDAO productNoticeDAO;

    public int insertProductNotice(ProductNoticeDTO productNoticeDTO) {
        return productNoticeDAO.insertProductNotice(productNoticeDTO);
    }

    public ProductNoticeDTO selectProductNotice(Integer noticeNo) {
        return productNoticeDAO.selectProductNotice(noticeNo);
    }

    public List<ProductNoticeDTO> selectProductNoticeList() {
        return productNoticeDAO.selectProductNoticeList();
    }

    public int updateProductNotice(ProductNoticeDTO productNoticeDTO) {
        return productNoticeDAO.updateProductNotice(productNoticeDTO);
    }

    public int deleteProductNotice(Integer noticeNo) {
        return productNoticeDAO.deleteProductNotice(noticeNo);
    }
}
