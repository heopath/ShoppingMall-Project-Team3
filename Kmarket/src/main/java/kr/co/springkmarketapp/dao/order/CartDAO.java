package kr.co.springkmarketapp.dao.order;

import kr.co.springkmarketapp.dto.order.CartDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartDAO {
    int insertCart(CartDTO cartDTO);
    CartDTO selectCart(Integer cartNo);
    List<CartDTO> selectCartList();
    int updateCart(CartDTO cartDTO);
    int deleteCart(Integer cartNo);
}
