package kr.co.springkmarketapp.service.order;

import jakarta.transaction.Transactional;
import kr.co.springkmarketapp.dao.order.ClaimFileDAO;
import kr.co.springkmarketapp.dao.order.OrderClaimDAO;
import kr.co.springkmarketapp.dao.order.OrderItemDAO;
import kr.co.springkmarketapp.dto.order.ClaimFileDTO;
import kr.co.springkmarketapp.dto.order.OrderClaimDTO;
import kr.co.springkmarketapp.service.notification.NotificationService;
import kr.co.springkmarketapp.util.FileStorageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderClaimService {

    private final OrderClaimDAO orderClaimDAO;
    // 주문 상품 상태 변경을 위해 필요한 DAO
    private final OrderItemDAO orderItemDAO;
    private final ClaimFileDAO claimFileDAO; // 1. ClaimFileDAO 주입
    private final FileStorageUtil fileStorageUtil; // 2. 유틸리티 주입
    private final NotificationService notificationService;


    public int insertOrderClaim(OrderClaimDTO orderClaimDTO) {
        return orderClaimDAO.insertOrderClaim(orderClaimDTO);
    }

    public OrderClaimDTO selectOrderClaim(Long claimNo) {
        return orderClaimDAO.selectOrderClaim(claimNo);
    }

    public List<OrderClaimDTO> selectOrderClaimList() {
        return orderClaimDAO.selectOrderClaimList();
    }

    @Transactional
    public int updateOrderClaim(OrderClaimDTO orderClaimDTO) {
        int result = orderClaimDAO.updateOrderClaim(orderClaimDTO);

        if (result == 1 && "반품완료".equals(orderClaimDTO.getStatus())) {
            notificationService.createReturnComplete(orderClaimDTO.getClaimNo());
        }

        return result;
    }

    public int deleteOrderClaim(Long claimNo) {
        return orderClaimDAO.deleteOrderClaim(claimNo);
    }

    @Transactional
    public void registerClaimWithFile(OrderClaimDTO claimDTO, String itemStatus, MultipartFile file) throws Exception {
        System.out.println(">>> 1. 클레임 삽입 시작");
        // 클레임 저장 (PK가 claimDTO에 주입됨)
        orderClaimDAO.insertOrderClaim(claimDTO);
        System.out.println(">>> 2. 클레임 삽입 완료, PK: " + claimDTO.getClaimNo());

        System.out.println(">>> 3. 주문 상품 상태 변경 시작: " + claimDTO.getOrderItemNo());
        // 주문 상품 상태 변경
        orderItemDAO.updateItemStatus(claimDTO.getOrderItemNo(), itemStatus);
        System.out.println(">>> 4. 주문 상품 상태 변경 완료");

        // 파일 처리
        if (file != null && !file.isEmpty()) {
            String filePath = fileStorageUtil.saveFile(file, "claims");

            ClaimFileDTO fileDTO = new ClaimFileDTO();
            fileDTO.setClaimNo(claimDTO.getClaimNo()); // 생성된 클레임 PK 사용
            fileDTO.setOriName(file.getOriginalFilename());
            fileDTO.setNewName(filePath);

            claimFileDAO.insertClaimFile(fileDTO);
        }
    }
}
