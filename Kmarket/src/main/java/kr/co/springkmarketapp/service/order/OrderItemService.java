package kr.co.springkmarketapp.service.order;

import kr.co.springkmarketapp.dao.order.OrderItemDAO;
import kr.co.springkmarketapp.dto.order.OrderItemDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemService {

    private final OrderItemDAO orderItemDAO;

    public int insertOrderItem(OrderItemDTO orderItemDTO) {
        return orderItemDAO.insertOrderItem(orderItemDTO);
    }

    public OrderItemDTO selectOrderItem(Long orderItemNo) {
        return orderItemDAO.selectOrderItem(orderItemNo);
    }

    public List<OrderItemDTO> selectOrderItemList() {
        return orderItemDAO.selectOrderItemList();
    }

    public int updateOrderItem(OrderItemDTO orderItemDTO) {
        return orderItemDAO.updateOrderItem(orderItemDTO);
    }

    public int deleteOrderItem(Long orderItemNo) {
        return orderItemDAO.deleteOrderItem(orderItemNo);
    }
}
