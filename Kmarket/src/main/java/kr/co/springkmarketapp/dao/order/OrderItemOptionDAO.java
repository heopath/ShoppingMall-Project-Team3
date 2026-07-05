package kr.co.springkmarketapp.dao.order;

import kr.co.springkmarketapp.dto.order.OrderItemOptionDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderItemOptionDAO {
    int insertOrderItemOption(OrderItemOptionDTO orderItemOptionDTO);
    OrderItemOptionDTO selectOrderItemOption(Long orderItemOptionNo);
    List<OrderItemOptionDTO> selectOrderItemOptionList();
    int updateOrderItemOption(OrderItemOptionDTO orderItemOptionDTO);
    int deleteOrderItemOption(Long orderItemOptionNo);
}
