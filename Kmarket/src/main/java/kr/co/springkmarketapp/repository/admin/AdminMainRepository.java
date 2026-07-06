package kr.co.springkmarketapp.repository.admin;

import kr.co.springkmarketapp.entity.order.Orders; // 본인의 프로젝트 엔티티 경로 확인 필요
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AdminMainRepository extends JpaRepository<Orders, Long> {

    // 1. 운영현황 상단 카드 수치
    @Query(value = "SELECT " +
            "(SELECT COUNT(*) FROM orders WHERE payment_status = '결제대기') AS depositWaitingCount, " +
            "(SELECT COUNT(*) FROM delivery WHERE delivery_status = '배송준비') AS deliveryPrepareCount, " +
            "(SELECT COUNT(*) FROM order_item WHERE item_status = '취소') AS cancelRequestCount, " +
            "(SELECT COUNT(*) FROM order_claim WHERE claim_type = 'EXCHANGE' AND status = '신청') AS exchangeRequestCount, " +
            "(SELECT COUNT(*) FROM order_claim WHERE claim_type = 'RETURN' AND status = '신청') AS refundRequestCount",
            nativeQuery = true)
    Map<String, Object> findOperationCardCounts();

    // 2. 오늘 및 어제 요약 데이터
    @Query(value = "SELECT " +
            "COUNT(CASE WHEN DATE(order_date) = CURDATE() THEN 1 END) AS orderCountToday, " +
            "COUNT(CASE WHEN DATE(order_date) = DATE_SUB(CURDATE(), INTERVAL 1 DAY) THEN 1 END) AS orderCountYesterday, " +
            "IFNULL(SUM(CASE WHEN DATE(order_date) = CURDATE() THEN pay_price END), 0) AS orderPriceToday, " +
            "IFNULL(SUM(CASE WHEN DATE(order_date) = DATE_SUB(CURDATE(), INTERVAL 1 DAY) THEN pay_price END), 0) AS orderPriceYesterday, " +
            "(SELECT COUNT(*) FROM member WHERE DATE(reg_date) = CURDATE()) AS memberRegisterToday, " +
            "(SELECT COUNT(*) FROM member WHERE DATE(reg_date) = DATE_SUB(CURDATE(), INTERVAL 1 DAY)) AS memberRegisterYesterday, " +
            "IFNULL((SELECT visit_count FROM visit_daily WHERE visit_date = CURDATE()), 0) AS visitorCountToday, " +
            "IFNULL((SELECT visit_count FROM visit_daily WHERE visit_date = DATE_SUB(CURDATE(), INTERVAL 1 DAY)), 0) AS visitorCountYesterday, " +
            "(SELECT COUNT(*) FROM qna WHERE DATE(reg_date) = CURDATE()) AS qnaCountToday, " +
            "(SELECT COUNT(*) FROM qna WHERE DATE(reg_date) = DATE_SUB(CURDATE(), INTERVAL 1 DAY)) AS qnaCountYesterday " +
            "FROM orders",
            nativeQuery = true)
    Map<String, Object> findOperationSummary();

    // 3. 최근 5일 막대그래프 집계 (주문 없는 날짜도 0으로 채워서 항상 5행 반환)
    @Query(value = "SELECT " +
            "DATE_FORMAT(d.dt, '%m-%d') AS dateStr, " +
            "COUNT(o.order_no) AS orderCnt, " +
            "COUNT(CASE WHEN o.payment_status = '결제완료' THEN 1 END) AS payCnt, " +
            "COUNT(CASE WHEN o.order_status LIKE '%반품%' OR o.payment_status = '결제취소' THEN 1 END) AS cancelCnt " +
            "FROM ( " +
            "    SELECT CURDATE() - INTERVAL 4 DAY AS dt " +
            "    UNION ALL SELECT CURDATE() - INTERVAL 3 DAY " +
            "    UNION ALL SELECT CURDATE() - INTERVAL 2 DAY " +
            "    UNION ALL SELECT CURDATE() - INTERVAL 1 DAY " +
            "    UNION ALL SELECT CURDATE() " +
            ") d " +
            "LEFT JOIN orders o ON DATE(o.order_date) = d.dt " +
            "GROUP BY d.dt " +
            "ORDER BY d.dt ASC",
            nativeQuery = true)
    List<Map<String, Object>> findRecentBarChartData();

    // 4. 카테고리별 비중 (원형 차트용) - 세부 카테고리를 4개 대분류로 그룹핑
    @Query(value = "SELECT " +
            "CASE " +
            "    WHEN c.cate_name IN ('휴대폰·태블릿','PC·노트북','카메라·주변기기','TV·영상가전','주방가전','생활가전') THEN '가전' " +
            "    WHEN c.cate_name IN ('신선식품','가공식품','건강식품') THEN '식품' " +
            "    WHEN c.cate_name IN ('여성의류','남성의류','유아동의류','신발','가방','지갑·벨트','스포츠의류·운동화') THEN '의류' " +
            "    ELSE '기타' " +
            "END AS categoryName, " +
            "(COUNT(*) * 100.0 / (SELECT COUNT(*) FROM order_item)) AS ratio " +
            "FROM order_item oi " +
            "JOIN product p ON oi.product_no = p.product_no " +
            "JOIN category c ON p.cate_no = c.cate_no " +
            "GROUP BY " +
            "CASE " +
            "    WHEN c.cate_name IN ('휴대폰·태블릿','PC·노트북','카메라·주변기기','TV·영상가전','주방가전','생활가전') THEN '가전' " +
            "    WHEN c.cate_name IN ('신선식품','가공식품','건강식품') THEN '식품' " +
            "    WHEN c.cate_name IN ('여성의류','남성의류','유아동의류','신발','가방','지갑·벨트','스포츠의류·운동화') THEN '의류' " +
            "    ELSE '기타' " +
            "END",
            nativeQuery = true)
    List<Map<String, Object>> findCategoryPieChartData();

    // 5. 최근 공지사항 5개
    @Query(value = "SELECT notice_no AS id, title, (SELECT member_id FROM member WHERE member_no = n.writer_no) AS writer, DATE_FORMAT(reg_date, '%Y-%m-%d') AS regDate " +
            "FROM notice n " +
            "ORDER BY reg_date DESC " +
            "LIMIT 5",
            nativeQuery = true)
    List<Map<String, Object>> findRecentNotices();

    // 6. 최근 고객문의 5개
    @Query(value = "SELECT qna_no AS id, title, (SELECT member_id FROM member WHERE member_no = q.member_no) AS writer, DATE_FORMAT(reg_date, '%Y-%m-%d') AS regDate " +
            "FROM qna q " +
            "ORDER BY reg_date DESC " +
            "LIMIT 5",
            nativeQuery = true)
    List<Map<String, Object>> findRecentQnas();
}