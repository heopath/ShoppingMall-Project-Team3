package kr.co.springkmarketapp.service.notification;

import kr.co.springkmarketapp.dao.notification.NotificationDAO;
import kr.co.springkmarketapp.dto.notification.NotificationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationDAO notificationDAO;

    @Transactional(readOnly = true)
    public List<NotificationDTO> getNotifications(Integer memberNo) {
        return notificationDAO.selectNotificationsByMemberNo(memberNo);
    }

    @Transactional(readOnly = true)
    public int getUnreadCount(Integer memberNo) {
        return notificationDAO.selectUnreadCount(memberNo);
    }

    @Transactional
    public void markAsRead(Long notificationNo, Integer memberNo) {
        notificationDAO.updateNotificationRead(notificationNo, memberNo);
    }

    @Transactional
    public void deleteNotification(Long notificationNo, Integer memberNo) {
        notificationDAO.deleteNotification(notificationNo, memberNo);
    }

    public void createOrderComplete(Long orderNo) {
        NotificationDTO target = notificationDAO.selectOrderNotificationTarget(orderNo);
        if (target == null) {
            return;
        }

        insert(target, "ORDER_COMPLETE", "주문이 완료되었습니다",
                "주문번호 " + target.getOrderCode() + " 주문이 정상적으로 접수되었습니다.",
                "ORDER_COMPLETE:" + orderNo, null);
    }

    public void createDeliveryShipping(Integer deliveryNo) {
        NotificationDTO target = notificationDAO.selectDeliveryNotificationTarget(deliveryNo);
        if (target == null) {
            return;
        }

        insert(target, "DELIVERY_SHIPPING", "배송이 시작되었습니다",
                "주문번호 " + target.getOrderCode() + " 상품이 출발했습니다.",
                "DELIVERY_SHIPPING:" + deliveryNo, deliveryNo);
    }

    public void createDeliveryComplete(Integer deliveryNo) {
        NotificationDTO target = notificationDAO.selectDeliveryNotificationTarget(deliveryNo);
        if (target == null) {
            return;
        }

        insert(target, "DELIVERY_COMPLETE", "배송이 완료되었습니다",
                "주문번호 " + target.getOrderCode() + " 상품이 배송 완료되었습니다.",
                "DELIVERY_COMPLETE:" + deliveryNo, deliveryNo);
    }

    public void createReturnComplete(Long claimNo) {
        NotificationDTO target = notificationDAO.selectClaimNotificationTarget(claimNo);
        if (target == null || !"RETURN".equalsIgnoreCase(target.getClaimType())) {
            return;
        }

        insert(target, "RETURN_COMPLETE", "반품이 완료되었습니다",
                "주문번호 " + target.getOrderCode() + " 상품의 반품 처리가 완료되었습니다.",
                "RETURN_COMPLETE:" + claimNo, null);
    }

    private void insert(NotificationDTO target,
                        String type,
                        String title,
                        String content,
                        String eventKey,
                        Integer deliveryNo) {
        notificationDAO.insertNotification(NotificationDTO.builder()
                .memberNo(target.getMemberNo())
                .notificationType(type)
                .title(title)
                .content(content)
                .linkUrl("/my/order")
                .orderNo(target.getOrderNo())
                .deliveryNo(deliveryNo)
                .eventKey(eventKey)
                .build());
    }
}
