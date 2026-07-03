package kr.co.springkmarketapp.service.order;

import kr.co.springkmarketapp.dao.order.OrdersDAO;
import kr.co.springkmarketapp.dto.order.OrdersDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersService {

    private final OrdersDAO ordersDAO;

    public int insertOrders(OrdersDTO ordersDTO) {
        return ordersDAO.insertOrders(ordersDTO);
    }

    public OrdersDTO selectOrders(Long orderNo) {
        return ordersDAO.selectOrders(orderNo);
    }

    public List<OrdersDTO> selectOrdersList() {
        return ordersDAO.selectOrdersList();
    }

    public int updateOrders(OrdersDTO ordersDTO) {
        return ordersDAO.updateOrders(ordersDTO);
    }

    public int deleteOrders(Long orderNo) {
        return ordersDAO.deleteOrders(orderNo);
    }
}
