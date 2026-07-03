package kr.co.springkmarketapp.service.order;

import kr.co.springkmarketapp.dao.order.DeliveryDAO;
import kr.co.springkmarketapp.dto.order.DeliveryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryDAO deliveryDAO;

    public int insertDelivery(DeliveryDTO deliveryDTO) {
        return deliveryDAO.insertDelivery(deliveryDTO);
    }

    public DeliveryDTO selectDelivery(Integer deliveryNo) {
        return deliveryDAO.selectDelivery(deliveryNo);
    }

    public List<DeliveryDTO> selectDeliveryList() {
        return deliveryDAO.selectDeliveryList();
    }

    public int updateDelivery(DeliveryDTO deliveryDTO) {
        return deliveryDAO.updateDelivery(deliveryDTO);
    }

    public int deleteDelivery(Integer deliveryNo) {
        return deliveryDAO.deleteDelivery(deliveryNo);
    }
}
