package kr.co.springkmarketapp.service.product;

import kr.co.springkmarketapp.dao.product.CategoryDAO;
import kr.co.springkmarketapp.dto.product.CategoryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryDAO categoryDAO;

    public int insertCategory(CategoryDTO categoryDTO) {
        return categoryDAO.insertCategory(categoryDTO);
    }

    public CategoryDTO selectCategory(Integer cateNo) {
        return categoryDAO.selectCategory(cateNo);
    }

    public List<CategoryDTO> selectCategoryList() {
        return categoryDAO.selectCategoryList();
    }

    public int updateCategory(CategoryDTO categoryDTO) {
        return categoryDAO.updateCategory(categoryDTO);
    }

    public int deleteCategory(Integer cateNo) {
        return categoryDAO.deleteCategory(cateNo);
    }

    // 카테고리 목록 리스트 출력
    public List<CategoryDTO> getCategoryTree() {
        List<CategoryDTO> mainCategories = categoryDAO.selectMainCategories();
        List<CategoryDTO> subCategories = categoryDAO.selectSubCategories();

        for (CategoryDTO main : mainCategories) {
            // NullPointerException 방지 처리가 안되어있다면 초기화 보완
            if (main.getChildren() == null) {
                main.setChildren(new ArrayList<>());
            }
            for (CategoryDTO sub : subCategories) {
                if (sub.getParentNo() != null && sub.getParentNo().equals(main.getCateNo())) {
                    main.getChildren().add(sub);
                }
            }
        }
        return mainCategories;
    }

    public CategoryDTO getCategory(int cateNo) {
        return categoryDAO.selectCategoryByNo(cateNo);
    }

    // =========================================================================
    // 🛠️ [관리자 페이지용 추가 보완 메서드]
    // =========================================================================

     // 관리자 카테고리 설정 화면 진입 시 1차 카테고리 selectbox를 채우기 위한 메서드
    public List<CategoryDTO> selectMainCategories() {
        return categoryDAO.selectMainCategories();
    }

    /**
     * 관리자가 1차 카테고리를 선택했을 때, 자바스크립트(Fetch/Axios) 요청을 받아
     * 해당 부모 밑에 있는 2차 중분류 목록만 실시간으로 반환해 주는 메서드
     */
    public List<CategoryDTO> selectSubCategoriesByParent(Integer parentNo) {
        List<CategoryDTO> allSubs = categoryDAO.selectSubCategories();
        List<CategoryDTO> filteredSubs = new ArrayList<>();

        if (allSubs != null && parentNo != null) {
            for (CategoryDTO sub : allSubs) {
                if (parentNo.equals(sub.getParentNo())) {
                    filteredSubs.add(sub);
                }
            }
        }
        return filteredSubs;
    }
}
