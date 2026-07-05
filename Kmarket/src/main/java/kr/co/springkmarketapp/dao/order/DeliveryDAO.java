package kr.co.springkmarketapp.dao.order;

import kr.co.springkmarketapp.dto.order.DeliveryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeliveryDAO {
    int insertDelivery(DeliveryDTO deliveryDTO);
    DeliveryDTO selectDelivery(Integer deliveryNo);
    List<DeliveryDTO> selectDeliveryList();
    int updateDelivery(DeliveryDTO deliveryDTO);
    int deleteDelivery(Integer deliveryNo);
}
