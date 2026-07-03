package kr.co.springkmarketapp.dao.order;

import kr.co.springkmarketapp.dto.order.OrderItemDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderItemDAO {
    int insertOrderItem(OrderItemDTO orderItemDTO);
    OrderItemDTO selectOrderItem(Long orderItemNo);
    List<OrderItemDTO> selectOrderItemList();
    int updateOrderItem(OrderItemDTO orderItemDTO);
    int deleteOrderItem(Long orderItemNo);
}
