package kr.co.springkmarketapp.service.order;

import kr.co.springkmarketapp.dao.order.OrderClaimDAO;
import kr.co.springkmarketapp.dto.order.OrderClaimDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderClaimService {

    private final OrderClaimDAO orderClaimDAO;

    public int insertOrderClaim(OrderClaimDTO orderClaimDTO) {
        return orderClaimDAO.insertOrderClaim(orderClaimDTO);
    }

    public OrderClaimDTO selectOrderClaim(Long claimNo) {
        return orderClaimDAO.selectOrderClaim(claimNo);
    }

    public List<OrderClaimDTO> selectOrderClaimList() {
        return orderClaimDAO.selectOrderClaimList();
    }

    public int updateOrderClaim(OrderClaimDTO orderClaimDTO) {
        return orderClaimDAO.updateOrderClaim(orderClaimDTO);
    }

    public int deleteOrderClaim(Long claimNo) {
        return orderClaimDAO.deleteOrderClaim(claimNo);
    }
}
