package kr.co.springkmarketapp.dao.cs;

import kr.co.springkmarketapp.dto.cs.CsCategoryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CsCategoryDAO {
    int insertCsCategory(CsCategoryDTO csCategoryDTO);
    CsCategoryDTO selectCsCategory(Integer csCateNo);
    List<CsCategoryDTO> selectCsCategoryList();
    int updateCsCategory(CsCategoryDTO csCategoryDTO);
    int deleteCsCategory(Integer csCateNo);
    List<CsCategoryDTO> selectParentCategoryList();
    List<CsCategoryDTO> selectChildCategoryList(int parentNo);
}
