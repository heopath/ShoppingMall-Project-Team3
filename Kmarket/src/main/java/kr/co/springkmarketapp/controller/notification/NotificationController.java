package kr.co.springkmarketapp.controller.notification;

import kr.co.springkmarketapp.config.MyUserDetails;
import kr.co.springkmarketapp.dto.notification.NotificationDTO;
import kr.co.springkmarketapp.service.notification.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping
    public Map<String, Object> getNotifications(
            @AuthenticationPrincipal MyUserDetails userDetails
    ) {
        Integer memberNo = userDetails.getMember().getMemberNo();
        List<NotificationDTO> notifications = notificationService.getNotifications(memberNo);

        return Map.of(
                "notifications", notifications,
                "unreadCount", notificationService.getUnreadCount(memberNo)
        );
    }

    @PostMapping("/{notificationNo}/read")
    public ResponseEntity<Void> markAsRead(
            @PathVariable Long notificationNo,
            @AuthenticationPrincipal MyUserDetails userDetails
    ) {
        notificationService.markAsRead(notificationNo, userDetails.getMember().getMemberNo());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{notificationNo}")
    public ResponseEntity<Void> deleteNotification(
            @PathVariable Long notificationNo,
            @AuthenticationPrincipal MyUserDetails userDetails
    ) {
        notificationService.deleteNotification(notificationNo, userDetails.getMember().getMemberNo());
        return ResponseEntity.noContent().build();
    }
}
