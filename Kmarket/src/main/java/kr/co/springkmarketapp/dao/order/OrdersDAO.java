package kr.co.springkmarketapp.dao.order;

import kr.co.springkmarketapp.dto.my.MemberPointDTO;
import kr.co.springkmarketapp.dto.order.*;
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

    int insertOrderItemOption(OrderItemOptionDTO orderItemOptionDTO);

    int updateMemberPointUse(@Param("memberNo") Integer memberNo,
                             @Param("usedPoint") Integer usedPoint);

    int updateMemberPointEarn(@Param("memberNo") Integer memberNo,
                              @Param("pointSave") Integer pointSave);

    int insertMemberPoint(MemberPointDTO memberPointDTO);

    OrdersDTO selectOrderComplete(
            @Param("memberNo") Integer memberNo,
            @Param("orderNo") Long orderNo
    );

    List<OrderItemDTO> selectOrderCompleteItems(
            @Param("orderNo") Long orderNo
    );

    DeliveryDTO selectOrderCompleteDelivery(
            @Param("orderNo") Long orderNo
    );
}
