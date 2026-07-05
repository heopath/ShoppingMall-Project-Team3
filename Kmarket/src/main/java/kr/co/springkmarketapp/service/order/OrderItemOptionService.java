package kr.co.springkmarketapp.service.order;

import kr.co.springkmarketapp.dao.order.OrderItemOptionDAO;
import kr.co.springkmarketapp.dto.order.OrderItemOptionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemOptionService {

    private final OrderItemOptionDAO orderItemOptionDAO;

    public int insertOrderItemOption(OrderItemOptionDTO orderItemOptionDTO) {
        return orderItemOptionDAO.insertOrderItemOption(orderItemOptionDTO);
    }

    public OrderItemOptionDTO selectOrderItemOption(Long orderItemOptionNo) {
        return orderItemOptionDAO.selectOrderItemOption(orderItemOptionNo);
    }

    public List<OrderItemOptionDTO> selectOrderItemOptionList() {
        return orderItemOptionDAO.selectOrderItemOptionList();
    }

    public int updateOrderItemOption(OrderItemOptionDTO orderItemOptionDTO) {
        return orderItemOptionDAO.updateOrderItemOption(orderItemOptionDTO);
    }

    public int deleteOrderItemOption(Long orderItemOptionNo) {
        return orderItemOptionDAO.deleteOrderItemOption(orderItemOptionNo);
    }
}
