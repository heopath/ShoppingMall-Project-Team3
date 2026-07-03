package kr.co.springkmarketapp.dao.order;

import kr.co.springkmarketapp.dto.order.OrdersDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrdersDAO {
    int insertOrders(OrdersDTO ordersDTO);
    OrdersDTO selectOrders(Long orderNo);
    List<OrdersDTO> selectOrdersList();
    int updateOrders(OrdersDTO ordersDTO);
    int deleteOrders(Long orderNo);
}
