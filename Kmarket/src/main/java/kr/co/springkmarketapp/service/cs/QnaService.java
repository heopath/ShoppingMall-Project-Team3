package kr.co.springkmarketapp.service.cs;

import kr.co.springkmarketapp.dao.cs.QnaDAO;
import kr.co.springkmarketapp.dao.my.MyDAO;
import kr.co.springkmarketapp.dto.cs.QnaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QnaService {

    private final QnaDAO qnaDAO;
    private final MyDAO myDAO;


    public int insertQna(QnaDTO qnaDTO) {
        return qnaDAO.insertQna(qnaDTO);
    }

    public QnaDTO selectQna(Long qnaNo) {
        return qnaDAO.selectQna(qnaNo);
    }


    // 고객센터 메인용
    public List<QnaDTO> selectQnaList() {
        return qnaDAO.selectQnaList();
    }

    // 마이페이지용
    public List<QnaDTO> selectQnaList(Integer memberNo) {
        return myDAO.selectMyQnaList(memberNo);
    }

    public List<QnaDTO> selectQnaListPage(int parentNo, int offset, int size) {
        return qnaDAO.selectQnaListPage(parentNo, offset, size);
    }

    // 관리자 페이지에서의 1차2차카테고리 조회를위해 부모카테고리 조인
    public List<QnaDTO> selectAdminQnaList() {return qnaDAO.selectAdminQnaList();}


    public int updateQna(QnaDTO qnaDTO) {
        return qnaDAO.updateQna(qnaDTO);
    }

    public int deleteQna(Long qnaNo) {
        return qnaDAO.deleteQna(qnaNo);
    }

    public int countQna(int parentNo) {
        return qnaDAO.countQna(parentNo);
    }


}
