package kr.co.springkmarketapp.dao.company;

import kr.co.springkmarketapp.dto.company.RecruitDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RecruitDAO {
    int insertRecruit(RecruitDTO recruitDTO);
    RecruitDTO selectRecruit(Integer recruitNo);
    List<RecruitDTO> selectRecruitList();
    List<RecruitDTO> selectRecruitListByPage(@Param("searchType") String searchType,
                                              @Param("keyword") String keyword,
                                              @Param("status") String status,
                                              @Param("offset") int offset,
                                              @Param("limit") int limit);
    int countRecruitList(@Param("searchType") String searchType,
                         @Param("keyword") String keyword,
                         @Param("status") String status);
    int updateRecruit(RecruitDTO recruitDTO);
    int deleteRecruit(Integer recruitNo);
    int deleteRecruits(@Param("recruitNos") List<Integer> recruitNos);
}
