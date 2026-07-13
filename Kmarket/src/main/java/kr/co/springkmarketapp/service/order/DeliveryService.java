package kr.co.springkmarketapp.service.order;

import kr.co.springkmarketapp.dao.admin.AdminOrderDAO;
import kr.co.springkmarketapp.dao.order.DeliveryDAO;
import kr.co.springkmarketapp.dto.order.DeliveryDTO;
import kr.co.springkmarketapp.dto.order.DeliveryDetailDTO;
import kr.co.springkmarketapp.service.notification.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryDAO deliveryDAO;
    private final NotificationService notificationService;

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

    public List<DeliveryDetailDTO> getDeliveryDetailsByOrderNo(Long orderNo) {

        List<DeliveryDetailDTO> deliveries = deliveryDAO.selectDeliveryDetailsByOrderNo(orderNo);

        for (DeliveryDetailDTO delivery : deliveries) {
            delivery.setItems(deliveryDAO.selectDeliveryItems(delivery.getDeliveryNo()));
        }

        return deliveries;
    }

    @Transactional
    public void updateInvoiceByDeliveryNo(Integer deliveryNo,
                                          String courier,
                                          String invoiceNo,
                                          String memo) {

        if (deliveryNo == null) {
            throw new IllegalArgumentException("배송번호가 없습니다.");
        }

        if (courier == null || courier.isBlank()) {
            throw new IllegalArgumentException("택배사를 선택하세요.");
        }

        if (invoiceNo == null || invoiceNo.isBlank()) {
            throw new IllegalArgumentException("송장번호를 입력하세요.");
        }

        int result = deliveryDAO.updateInvoiceByDeliveryNo(
                deliveryNo,
                courier,
                invoiceNo,
                memo
        );

        if (result != 1) {
            throw new IllegalArgumentException("배송정보 수정에 실패했습니다.");
        }

        notificationService.createDeliveryShipping(deliveryNo);
    }

    @Transactional
    public void completeArrivedDeliveries() {
        List<Integer> deliveryNos = deliveryDAO.selectShippingDeliveryNosForCompletion();

        for (Integer deliveryNo : deliveryNos) {
            if (deliveryDAO.updateDeliveryComplete(deliveryNo) == 1) {
                notificationService.createDeliveryComplete(deliveryNo);
            }
        }
    }

}
