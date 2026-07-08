package kr.co.springkmarketapp.dao.order;

import kr.co.springkmarketapp.dto.order.DeliveryDTO;
import kr.co.springkmarketapp.dto.order.DeliveryDetailDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DeliveryDAO {

    // 기본 CRUD
    int insertDelivery(DeliveryDTO deliveryDTO);
    DeliveryDTO selectDelivery(Integer deliveryNo);
    List<DeliveryDTO> selectDeliveryAll();  // 기존 selectDeliveryList() → 이름 변경
    int updateDelivery(DeliveryDTO deliveryDTO);
    int deleteDelivery(Integer deliveryNo);

    // 관리자 배송현황 목록
    List<DeliveryDTO> selectDeliveryList(@Param("offset") int offset, @Param("size") int size,
                                         @Param("searchType") String searchType, @Param("keyword") String keyword);
    int selectDeliveryCount(@Param("searchType") String searchType, @Param("keyword") String keyword);

    // 관리자 배송상세
    DeliveryDetailDTO selectDeliveryDetail(@Param("deliveryNo") int deliveryNo);
    List<DeliveryDetailDTO.DeliveryItemDTO> selectDeliveryItems(@Param("deliveryNo") int deliveryNo);


    // 관리자 배송별 송장처리
    int updateInvoiceByDeliveryNo(@Param("deliveryNo") Integer deliveryNo,
                                  @Param("courier") String courier,
                                  @Param("invoiceNo") String invoiceNo,
                                  @Param("memo") String memo);

    // 주문번호 기준 배송목록 조회
    List<DeliveryDetailDTO> selectDeliveryDetailsByOrderNo(@Param("orderNo") Long orderNo);
}