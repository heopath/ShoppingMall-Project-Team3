package kr.co.springkmarketapp.dto.admin;

import lombok.*;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AdminMainDTO {

    // 1. 운영현황 상단 카운트 카드 데이터
    private long depositWaitingCount;   // 입금대기
    private long deliveryPrepareCount;  // 배송준비
    private long cancelRequestCount;    // 취소요청
    private long exchangeRequestCount;  // 교환요청
    private long refundRequestCount;    // 반품요청

    // 2. 운영현황 하단 요약 데이터 (오늘/어제)
    private long orderCountToday;
    private long orderCountYesterday;

    private long orderPriceToday;
    private long orderPriceYesterday;

    private long memberRegisterToday;
    private long memberRegisterYesterday;

    private long visitorCountToday;
    private long visitorCountYesterday;

    private long qnaCountToday;
    private long qnaCountYesterday;

    // 3. 집계현황 - 최근 5일 막대 차트 데이터 리스트
    private List<ChartDataDTO> barChartData;

    // 4. 집계현황 - 카테고리별 매출 비중 비율 (원형 차트 범례용)
    private Map<String, Double> pieChartData;

    // 5. 하단 최신 보드 리스트 스냅샷
    private List<BoardShortDTO> recentNotices;
    private List<BoardShortDTO> recentQnas;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ChartDataDTO {
        private String date;       // "10-10"
        private int orderCnt;      // 주문 건수 (숫자 표기용)
        private int payCnt;        // 결제 건수 (숫자 표기용)
        private int cancelCnt;     // 취소 건수 (숫자 표기용)
        private int orderHeight;   // 주문 style px 높이값
        private int payHeight;     // 결제 style px 높이값
        private int cancelHeight;  // 취소 style px 높이값
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BoardShortDTO {
        private Long id;
        private String title;
        private String writer;
        private String regDate;
    }
}