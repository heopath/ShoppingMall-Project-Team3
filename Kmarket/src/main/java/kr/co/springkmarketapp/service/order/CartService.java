package kr.co.springkmarketapp.service.order;

import kr.co.springkmarketapp.dao.order.CartDAO;
import kr.co.springkmarketapp.dto.order.CartDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartDAO cartDAO;

    public int insertCart(CartDTO cartDTO) {
        return cartDAO.insertCart(cartDTO);
    }

    public CartDTO selectCart(Integer cartNo) {
        return cartDAO.selectCart(cartNo);
    }

    public List<CartDTO> selectCartList() {
        return cartDAO.selectCartList();
    }

    public int updateCart(CartDTO cartDTO) {
        return cartDAO.updateCart(cartDTO);
    }

    public int deleteCart(Integer cartNo) {
        return cartDAO.deleteCart(cartNo);
    }
}
