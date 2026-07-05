package kr.co.springkmarketapp.dao.order;

import kr.co.springkmarketapp.dto.order.OrderClaimDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderClaimDAO {
    int insertOrderClaim(OrderClaimDTO orderClaimDTO);
    OrderClaimDTO selectOrderClaim(Long claimNo);
    List<OrderClaimDTO> selectOrderClaimList();
    int updateOrderClaim(OrderClaimDTO orderClaimDTO);
    int deleteOrderClaim(Long claimNo);
}
