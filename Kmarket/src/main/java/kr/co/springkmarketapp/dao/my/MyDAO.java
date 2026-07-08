package kr.co.springkmarketapp.dao.my;

import kr.co.springkmarketapp.dto.member.SellerProfileDTO;
import kr.co.springkmarketapp.dto.order.OrderItemDTO;
import kr.co.springkmarketapp.dto.order.OrdersDTO;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MyDAO {

    List<OrderItemDTO> selectRecentOrderList(Long memberNo);
    List<OrderItemDTO> selectOrderDetail(Long orderNo);
    SellerProfileDTO selectSellerDetail(Integer sellerNo);
}
