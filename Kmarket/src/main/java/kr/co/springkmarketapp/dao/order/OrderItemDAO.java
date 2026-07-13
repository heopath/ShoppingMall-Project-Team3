package kr.co.springkmarketapp.dao.order;

import kr.co.springkmarketapp.dto.order.OrderItemDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderItemDAO {
    int insertOrderItem(OrderItemDTO orderItemDTO);
    OrderItemDTO selectOrderItem(Long orderItemNo);
    List<OrderItemDTO> selectOrderItemList();
    int updateOrderItem(OrderItemDTO orderItemDTO);
    int deleteOrderItem(Long orderItemNo);

    int updateItemStatus(@Param("orderItemNo") Long orderItemNo, @Param("itemStatus") String itemStatus);
}