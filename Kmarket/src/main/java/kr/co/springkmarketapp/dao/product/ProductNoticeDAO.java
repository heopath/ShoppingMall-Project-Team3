package kr.co.springkmarketapp.dao.product;

import kr.co.springkmarketapp.dto.product.ProductNoticeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductNoticeDAO {
    int insertProductNotice(ProductNoticeDTO productNoticeDTO);
    ProductNoticeDTO selectProductNotice(Integer noticeNo);
    List<ProductNoticeDTO> selectProductNoticeList();
    int updateProductNotice(ProductNoticeDTO productNoticeDTO);
    int deleteProductNotice(Integer noticeNo);
}
