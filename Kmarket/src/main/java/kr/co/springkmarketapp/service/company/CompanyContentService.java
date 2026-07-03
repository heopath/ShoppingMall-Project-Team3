package kr.co.springkmarketapp.service.company;

import kr.co.springkmarketapp.dao.company.CompanyContentDAO;
import kr.co.springkmarketapp.dto.company.CompanyContentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyContentService {

    private final CompanyContentDAO companyContentDAO;

    public int insertCompanyContent(CompanyContentDTO companyContentDTO) {
        return companyContentDAO.insertCompanyContent(companyContentDTO);
    }

    public CompanyContentDTO selectCompanyContent(Integer contentNo) {
        return companyContentDAO.selectCompanyContent(contentNo);
    }

    public List<CompanyContentDTO> selectCompanyContentList() {
        return companyContentDAO.selectCompanyContentList();
    }

    public int updateCompanyContent(CompanyContentDTO companyContentDTO) {
        return companyContentDAO.updateCompanyContent(companyContentDTO);
    }

    public int deleteCompanyContent(Integer contentNo) {
        return companyContentDAO.deleteCompanyContent(contentNo);
    }
}
