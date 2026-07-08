package kr.co.springkmarketapp.service.cs;

import kr.co.springkmarketapp.dao.cs.CsCategoryDAO;
import kr.co.springkmarketapp.dto.cs.CsCategoryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CsCategoryService {

    private final CsCategoryDAO csCategoryDAO;

    public int insertCsCategory(CsCategoryDTO csCategoryDTO) {
        return csCategoryDAO.insertCsCategory(csCategoryDTO);
    }

    public CsCategoryDTO selectCsCategory(Integer csCateNo) {
        return csCategoryDAO.selectCsCategory(csCateNo);
    }

    public List<CsCategoryDTO> selectCsCategoryList() {
        return csCategoryDAO.selectCsCategoryList();
    }

    public int updateCsCategory(CsCategoryDTO csCategoryDTO) {
        return csCategoryDAO.updateCsCategory(csCategoryDTO);
    }

    public int deleteCsCategory(Integer csCateNo) {
        return csCategoryDAO.deleteCsCategory(csCateNo);
    }


    public List<CsCategoryDTO> selectParentCategoryList() {
        return csCategoryDAO.selectParentCategoryList();
    }

    public List<CsCategoryDTO> selectChildCategoryList(int parentNo) {
        return csCategoryDAO.selectChildCategoryList(parentNo);
    }
}
