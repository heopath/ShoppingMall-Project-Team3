package kr.co.springkmarketapp.service.cs;

import kr.co.springkmarketapp.dao.cs.QnaDAO;
import kr.co.springkmarketapp.dto.cs.QnaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QnaService {

    private final QnaDAO qnaDAO;

    public int insertQna(QnaDTO qnaDTO) {
        return qnaDAO.insertQna(qnaDTO);
    }

    public QnaDTO selectQna(Long qnaNo) {
        return qnaDAO.selectQna(qnaNo);
    }

    public List<QnaDTO> selectQnaList() {
        return qnaDAO.selectQnaList();
    }

    public int updateQna(QnaDTO qnaDTO) {
        return qnaDAO.updateQna(qnaDTO);
    }

    public int deleteQna(Long qnaNo) {
        return qnaDAO.deleteQna(qnaNo);
    }

    public int countQna(int parentNo) {
        return qnaDAO.countQna(parentNo);
    }

    public List<QnaDTO> selectQnaListLimit(int parentNo, int offset, int size) {
        return qnaDAO.selectQnaListLimit(parentNo, offset, size);
    }
}
