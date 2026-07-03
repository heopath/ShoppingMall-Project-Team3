package kr.co.springkmarketapp.repository.admin;

import kr.co.springkmarketapp.entity.admin.VisitDaily;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface VisitDailyRepository extends JpaRepository<VisitDaily, LocalDate> {
}
