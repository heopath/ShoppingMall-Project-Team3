package kr.co.springkmarketapp.dao.company;

import kr.co.springkmarketapp.dto.company.RecruitDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RecruitDAO {
    int insertRecruit(RecruitDTO recruitDTO);
    RecruitDTO selectRecruit(Integer recruitNo);
    List<RecruitDTO> selectRecruitList();
    int updateRecruit(RecruitDTO recruitDTO);
    int deleteRecruit(Integer recruitNo);
}
