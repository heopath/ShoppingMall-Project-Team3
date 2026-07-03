package kr.co.springkmarketapp.dao.company;

import kr.co.springkmarketapp.dto.company.CompanyContentDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CompanyContentDAO {
    int insertCompanyContent(CompanyContentDTO companyContentDTO);
    CompanyContentDTO selectCompanyContent(Integer contentNo);
    List<CompanyContentDTO> selectCompanyContentList();
    int updateCompanyContent(CompanyContentDTO companyContentDTO);
    int deleteCompanyContent(Integer contentNo);
}
