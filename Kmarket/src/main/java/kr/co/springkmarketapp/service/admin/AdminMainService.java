package kr.co.springkmarketapp.service.admin;

import kr.co.springkmarketapp.dto.admin.AdminMainDTO;
import kr.co.springkmarketapp.repository.admin.AdminMainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AdminMainService {

    private final AdminMainRepository adminMainRepository;

    public AdminMainDTO getDashboardData() {
        // 1. DB 쿼리 원천 데이터 조회
        Map<String, Object> cards = adminMainRepository.findOperationCardCounts();
        Map<String, Object> summary = adminMainRepository.findOperationSummary();
        List<Map<String, Object>> rawBarChart = adminMainRepository.findRecentBarChartData();
        List<Map<String, Object>> rawPieChart = adminMainRepository.findCategoryPieChartData();
        List<Map<String, Object>> rawNotices = adminMainRepository.findRecentNotices();
        List<Map<String, Object>> rawQnas = adminMainRepository.findRecentQnas();

        // 2. 막대차트 데이터 PX 보정 및 리스트 파싱
        List<AdminMainDTO.ChartDataDTO> barChartList = new ArrayList<>();
        for(Map<String, Object> row : rawBarChart) {
            String date = row.get("dateStr").toString();
            int orders = ((Number) row.get("orderCnt")).intValue();
            int pays = ((Number) row.get("payCnt")).intValue();
            int cancels = ((Number) row.get("cancelCnt")).intValue();

            // 데이터 분포에 알맞게 그래프 높이 스케일링 보정
            int orderHeight = Math.min(orders * 15, 120);
            int payHeight = Math.min(pays * 15, 120);
            int cancelHeight = Math.min(cancels * 15, 120);

            barChartList.add(new AdminMainDTO.ChartDataDTO(date, orderHeight, payHeight, cancelHeight));
        }

        // 3. 원형차트 카테고리 맵 가공
        Map<String, Double> pieChartMap = new LinkedHashMap<>();
        pieChartMap.put("가전", 0.0); pieChartMap.put("식품", 0.0);
        pieChartMap.put("의류", 0.0); pieChartMap.put("기타", 0.0);

        for(Map<String, Object> row : rawPieChart) {
            String name = row.get("categoryName").toString();
            Double ratio = Double.valueOf(row.get("ratio").toString());
            if(pieChartMap.containsKey(name)) {
                pieChartMap.put(name, ratio);
            } else {
                pieChartMap.put("기타", pieChartMap.get("기타") + ratio);
            }
        }

        // 4. 공지사항 리스트 파싱
        List<AdminMainDTO.BoardShortDTO> noticeList = new ArrayList<>();
        for(Map<String, Object> row : rawNotices) {
            noticeList.add(new AdminMainDTO.BoardShortDTO(
                    ((Number) row.get("id")).longValue(),
                    row.get("title").toString(),
                    row.get("writer").toString(),
                    row.get("regDate").toString()
            ));
        }

        // 5. 고객문의 리스트 파싱
        List<AdminMainDTO.BoardShortDTO> qnaList = new ArrayList<>();
        for(Map<String, Object> row : rawQnas) {
            qnaList.add(new AdminMainDTO.BoardShortDTO(
                    ((Number) row.get("id")).longValue(),
                    row.get("title").toString(),
                    row.get("writer").toString(),
                    row.get("regDate").toString()
            ));
        }

        // 6. 최종 통합 빌더 반환
        return AdminMainDTO.builder()
                .depositWaitingCount(((Number) cards.get("depositWaitingCount")).longValue())
                .deliveryPrepareCount(((Number) cards.get("deliveryPrepareCount")).longValue())
                .cancelRequestCount(((Number) cards.get("cancelRequestCount")).longValue())
                .exchangeRequestCount(((Number) cards.get("exchangeRequestCount")).longValue())
                .refundRequestCount(((Number) cards.get("refundRequestCount")).longValue())

                .orderCountToday(((Number) summary.get("orderCountToday")).longValue())
                .orderCountYesterday(((Number) summary.get("orderCountYesterday")).longValue())
                .orderPriceToday(((Number) summary.get("orderPriceToday")).longValue())
                .orderPriceYesterday(((Number) summary.get("orderPriceYesterday")).longValue())
                .memberRegisterToday(((Number) summary.get("memberRegisterToday")).longValue())
                .memberRegisterYesterday(((Number) summary.get("memberRegisterYesterday")).longValue())
                .visitorCountToday(((Number) summary.get("visitorCountToday")).longValue())
                .visitorCountYesterday(((Number) summary.get("visitorCountYesterday")).longValue())
                .qnaCountToday(((Number) summary.get("qnaCountToday")).longValue())
                .qnaCountYesterday(((Number) summary.get("qnaCountYesterday")).longValue())

                .barChartData(barChartList)
                .pieChartData(pieChartMap)
                .recentNotices(noticeList)
                .recentQnas(qnaList)
                .build();
    }
}