package kr.co.springkmarketapp.dto.notification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDTO {
    private Long notificationNo;
    private Integer memberNo;
    private String notificationType;
    private String title;
    private String content;
    private String linkUrl;
    private Long orderNo;
    private Integer deliveryNo;
    private String eventKey;
    private String readYn;
    private LocalDateTime regDate;
    private LocalDateTime readDate;

    // 알림 대상 조회용 JOIN 결과
    private String orderCode;
    private String claimType;
}
