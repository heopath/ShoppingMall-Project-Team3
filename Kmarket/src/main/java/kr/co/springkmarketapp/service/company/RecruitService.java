package kr.co.springkmarketapp.service.company;

import kr.co.springkmarketapp.dao.company.RecruitDAO;
import kr.co.springkmarketapp.dto.company.RecruitDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecruitService {

    private final RecruitDAO recruitDAO;

    public int insertRecruit(RecruitDTO recruitDTO) {
        return recruitDAO.insertRecruit(recruitDTO);
    }

    public RecruitDTO selectRecruit(Integer recruitNo) {
        return recruitDAO.selectRecruit(recruitNo);
    }

    public List<RecruitDTO> selectRecruitList() {
        return recruitDAO.selectRecruitList();
    }

    public int updateRecruit(RecruitDTO recruitDTO) {
        return recruitDAO.updateRecruit(recruitDTO);
    }

    public int deleteRecruit(Integer recruitNo) {
        return recruitDAO.deleteRecruit(recruitNo);
    }
}
