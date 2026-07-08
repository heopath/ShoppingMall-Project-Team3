package kr.co.springkmarketapp.dao.order;

import kr.co.springkmarketapp.dto.order.DeliveryDTO;
import kr.co.springkmarketapp.dto.order.OrderItemDTO;
import kr.co.springkmarketapp.dto.order.OrderSaveDTO;
import kr.co.springkmarketapp.dto.order.OrdersDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrdersDAO {
    int insertOrders(OrdersDTO ordersDTO);
    OrdersDTO selectOrders(Long orderNo);
    List<OrdersDTO> selectOrdersList();
    int updateOrders(OrdersDTO ordersDTO);
    int deleteOrders(Long orderNo);

    Integer selectAvailablePoint(
            @Param("memberNo") Integer memberNo
    );

    int insertOrder(OrderSaveDTO orderSaveDTO);

    int insertDelivery(DeliveryDTO deliverySaveDTO);

    int insertOrderItem(OrderItemDTO orderItemSaveDTO);

    int updateProductStock(
            @Param("productNo") Integer productNo,
            @Param("quantity") Integer quantity
    );
}
