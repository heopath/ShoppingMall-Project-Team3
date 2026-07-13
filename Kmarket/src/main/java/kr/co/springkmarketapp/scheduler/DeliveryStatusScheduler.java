package kr.co.springkmarketapp.scheduler;

import kr.co.springkmarketapp.service.order.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeliveryStatusScheduler {

    private final DeliveryService deliveryService;

    // 출고 시각부터 3일이 지난 배송을 1분마다 확인한다.
    @Scheduled(fixedDelay = 60000, initialDelay = 15000)
    public void completeArrivedDeliveries() {
        deliveryService.completeArrivedDeliveries();
    }
}
