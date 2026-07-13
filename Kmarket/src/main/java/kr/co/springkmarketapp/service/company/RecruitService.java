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

    public List<RecruitDTO> selectPublicRecruitList() {
        return recruitDAO.selectPublicRecruitList();
    }

    public RecruitDTO selectPublicRecruit(Integer recruitNo) {
        return recruitDAO.selectPublicRecruit(recruitNo);
    }

    public List<RecruitDTO> selectPublicRecruitList(String department, int offset, int limit) {
        return recruitDAO.selectPublicRecruitListByPage(
                normalizeKeyword(department),
                offset,
                limit
        );
    }

    public int countPublicRecruitList(String department) {
        return recruitDAO.countPublicRecruitList(normalizeKeyword(department));
    }

    public List<RecruitDTO> selectRecruitList(String searchType, String keyword, String status, int offset, int limit) {
        return recruitDAO.selectRecruitListByPage(searchType, normalizeKeyword(keyword), normalizeKeyword(status), offset, limit);
    }

    public int countRecruitList(String searchType, String keyword, String status) {
        return recruitDAO.countRecruitList(searchType, normalizeKeyword(keyword), normalizeKeyword(status));
    }

    public int updateRecruit(RecruitDTO recruitDTO) {
        return recruitDAO.updateRecruit(recruitDTO);
    }

    public int deleteRecruit(Integer recruitNo) {
        return recruitDAO.deleteRecruit(recruitNo);
    }

    public int deleteRecruits(List<Integer> recruitNos) {
        if (recruitNos == null || recruitNos.isEmpty()) return 0;
        return recruitDAO.deleteRecruits(recruitNos);
    }

    private String normalizeKeyword(String keyword) {
        return keyword == null || keyword.trim().isEmpty() ? null : keyword.trim();
    }
}
