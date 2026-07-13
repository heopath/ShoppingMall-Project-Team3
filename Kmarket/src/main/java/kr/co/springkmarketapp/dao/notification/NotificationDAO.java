package kr.co.springkmarketapp.dao.notification;

import kr.co.springkmarketapp.dto.notification.NotificationDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NotificationDAO {
    int insertNotification(NotificationDTO notificationDTO);
    List<NotificationDTO> selectNotificationsByMemberNo(@Param("memberNo") Integer memberNo);
    int selectUnreadCount(@Param("memberNo") Integer memberNo);
    int updateNotificationRead(@Param("notificationNo") Long notificationNo,
                               @Param("memberNo") Integer memberNo);
    int deleteNotification(@Param("notificationNo") Long notificationNo,
                           @Param("memberNo") Integer memberNo);

    NotificationDTO selectOrderNotificationTarget(@Param("orderNo") Long orderNo);
    NotificationDTO selectDeliveryNotificationTarget(@Param("deliveryNo") Integer deliveryNo);
    NotificationDTO selectClaimNotificationTarget(@Param("claimNo") Long claimNo);
}
