package kr.co.springkmarketapp.service.my;

import kr.co.springkmarketapp.dao.my.MyDAO;
import kr.co.springkmarketapp.dto.member.SellerProfileDTO;
import kr.co.springkmarketapp.dto.order.OrderItemDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyService {

    private final MyDAO myDAO;

    public List<OrderItemDTO> selectRecentOrderList(Long memberNo) {
        return myDAO.selectRecentOrderList(memberNo);
    }
    public List<OrderItemDTO> selectOrderDetail(Long orderNo) {
        return myDAO.selectOrderDetail(orderNo);
    }
    public SellerProfileDTO selectSellerDetail(Integer sellerNo){
        return myDAO.selectSellerDetail(sellerNo);
    }
}
