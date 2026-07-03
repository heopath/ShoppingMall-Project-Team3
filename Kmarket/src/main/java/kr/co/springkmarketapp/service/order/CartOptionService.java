package kr.co.springkmarketapp.service.order;

import kr.co.springkmarketapp.dao.order.CartOptionDAO;
import kr.co.springkmarketapp.dto.order.CartOptionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartOptionService {

    private final CartOptionDAO cartOptionDAO;

    public int insertCartOption(CartOptionDTO cartOptionDTO) {
        return cartOptionDAO.insertCartOption(cartOptionDTO);
    }

    public CartOptionDTO selectCartOption(CartOptionDTO cartOptionDTO) {
        return cartOptionDAO.selectCartOption(cartOptionDTO);
    }

    public List<CartOptionDTO> selectCartOptionList() {
        return cartOptionDAO.selectCartOptionList();
    }

    public int updateCartOption(CartOptionDTO cartOptionDTO) {
        return cartOptionDAO.updateCartOption(cartOptionDTO);
    }

    public int deleteCartOption(CartOptionDTO cartOptionDTO) {
        return cartOptionDAO.deleteCartOption(cartOptionDTO);
    }
}
