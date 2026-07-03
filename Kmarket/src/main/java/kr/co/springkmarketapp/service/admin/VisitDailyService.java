package kr.co.springkmarketapp.service.admin;

import kr.co.springkmarketapp.dao.admin.VisitDailyDAO;
import kr.co.springkmarketapp.dto.admin.VisitDailyDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VisitDailyService {

    private final VisitDailyDAO visitDailyDAO;

    public int insertVisitDaily(VisitDailyDTO visitDailyDTO) {
        return visitDailyDAO.insertVisitDaily(visitDailyDTO);
    }

    public VisitDailyDTO selectVisitDaily(LocalDate visitDate) {
        return visitDailyDAO.selectVisitDaily(visitDate);
    }

    public List<VisitDailyDTO> selectVisitDailyList() {
        return visitDailyDAO.selectVisitDailyList();
    }

    public int updateVisitDaily(VisitDailyDTO visitDailyDTO) {
        return visitDailyDAO.updateVisitDaily(visitDailyDTO);
    }

    public int deleteVisitDaily(LocalDate visitDate) {
        return visitDailyDAO.deleteVisitDaily(visitDate);
    }
}
