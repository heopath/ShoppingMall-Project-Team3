package kr.co.springkmarketapp.dao.admin;

import kr.co.springkmarketapp.dto.admin.VisitDailyDTO;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface VisitDailyDAO {
    int insertVisitDaily(VisitDailyDTO visitDailyDTO);
    VisitDailyDTO selectVisitDaily(LocalDate visitDate);
    List<VisitDailyDTO> selectVisitDailyList();
    int updateVisitDaily(VisitDailyDTO visitDailyDTO);
    int deleteVisitDaily(LocalDate visitDate);
}
