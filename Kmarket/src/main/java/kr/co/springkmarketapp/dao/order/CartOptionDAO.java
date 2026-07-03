package kr.co.springkmarketapp.dao.order;

import kr.co.springkmarketapp.dto.order.CartOptionDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartOptionDAO {
    int insertCartOption(CartOptionDTO cartOptionDTO);
    CartOptionDTO selectCartOption(CartOptionDTO cartOptionDTO);
    List<CartOptionDTO> selectCartOptionList();
    int updateCartOption(CartOptionDTO cartOptionDTO);
    int deleteCartOption(CartOptionDTO cartOptionDTO);
}
