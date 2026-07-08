package kr.co.springkmarketapp.service.admin;

import kr.co.springkmarketapp.dao.admin.AdminOrderDAO;
import kr.co.springkmarketapp.dto.admin.AdminOrderDetailDTO;
import kr.co.springkmarketapp.dto.admin.AdminOrderListDTO;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminOrderService {

    private final AdminOrderDAO adminOrderDAO;

    public AdminOrderService(AdminOrderDAO adminOrderDAO) {
        this.adminOrderDAO = adminOrderDAO;
    }

    public Map<String, Object> getAdminOrderList(int page, String searchType, String keyword) {
        int size = 10; // 한 페이지에 10개 출력
        int offset = (page - 1) * size;

        List<AdminOrderListDTO> list = adminOrderDAO.selectAdminOrderList(offset, size, searchType, keyword);
        int totalItems = adminOrderDAO.selectAdminOrderCount(searchType, keyword);
        int totalPages = (int) Math.ceil((double) totalItems / size);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("list", list);
        resultMap.put("totalPages", totalPages);
        return resultMap;
    }

    public void updateDeliveryInvoice(Long orderNo, String courier, String invoiceNo,
                                      String receiverName, String receiverHp,
                                      String zip, String addr1, String addr2, String memo) {
        Map<String, Object> params = new HashMap<>();
        params.put("orderNo", orderNo);
        params.put("courier", courier);
        params.put("invoiceNo", invoiceNo);
        params.put("sellerNo", 1); // 추후 order_item 기준으로 실제 seller_no 조회 필요
        params.put("receiverName", receiverName);
        params.put("receiverHp", receiverHp);
        params.put("zip", zip);
        params.put("addr1", addr1);
        params.put("addr2", addr2);
        params.put("memo", memo);

        adminOrderDAO.insertOrUpdateDelivery(params);
    }

    public AdminOrderDetailDTO getOrderDetail(Long orderNo) {
        return adminOrderDAO.selectAdminOrderDetail(orderNo);
    }

    public List<AdminOrderDetailDTO.OrderItemDetail> getOrderItemDetails(Long orderNo) {
        return adminOrderDAO.selectAdminOrderItemDetails(orderNo);
    }
}