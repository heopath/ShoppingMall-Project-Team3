package kr.co.springkmarketapp.dao.admin;

import kr.co.springkmarketapp.dto.admin.AdminOrderDetailDTO;
import kr.co.springkmarketapp.dto.admin.AdminOrderListDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

@Mapper
public interface AdminOrderDAO {
    List<AdminOrderListDTO> selectAdminOrderList(
            @Param("offset") int offset,
            @Param("size") int size,
            @Param("searchType") String searchType,
            @Param("keyword") String keyword
    );

    int selectAdminOrderCount(@Param("searchType") String searchType, @Param("keyword") String keyword);

    int insertOrUpdateDelivery(Map<String, Object> paramMap);

    AdminOrderDetailDTO selectAdminOrderDetail(@Param("orderNo") Long orderNo);
    List<AdminOrderDetailDTO.OrderItemDetail> selectAdminOrderItemDetails(@Param("orderNo") Long orderNo);
}
