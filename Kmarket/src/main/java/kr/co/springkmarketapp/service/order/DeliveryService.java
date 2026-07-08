package kr.co.springkmarketapp.service.order;

import kr.co.springkmarketapp.dao.admin.AdminOrderDAO;
import kr.co.springkmarketapp.dao.order.DeliveryDAO;
import kr.co.springkmarketapp.dto.order.DeliveryDTO;
import kr.co.springkmarketapp.dto.order.DeliveryDetailDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        return deliveryDAO.selectDeliveryAll();
    }

    public int updateDelivery(DeliveryDTO deliveryDTO) {
        return deliveryDAO.updateDelivery(deliveryDTO);
    }

    public int deleteDelivery(Integer deliveryNo) {
        return deliveryDAO.deleteDelivery(deliveryNo);
    }

    public Map<String, Object> getDeliveryList(int page, String searchType, String keyword) {
        int size = 10;
        int offset = (page - 1) * size;

        List<DeliveryDTO> list = deliveryDAO.selectDeliveryList(offset, size, searchType, keyword);
        int total = deliveryDAO.selectDeliveryCount(searchType, keyword);
        int totalPages = (int) Math.ceil((double) total / size);

        Map<String, Object> result = new HashMap<>();
        result.put("list", list);
        result.put("totalPages", totalPages);
        return result;
    }

    public DeliveryDetailDTO getDeliveryDetail(int deliveryNo) {
        DeliveryDetailDTO detail = deliveryDAO.selectDeliveryDetail(deliveryNo);
        detail.setItems(deliveryDAO.selectDeliveryItems(deliveryNo));
        return detail;
    }

}
