package kr.co.springkmarketapp.dao.cs;

import kr.co.springkmarketapp.dto.cs.QnaDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QnaDAO {
    int insertQna(QnaDTO qnaDTO);
    QnaDTO selectQna(Long qnaNo);
    List<QnaDTO> selectQnaList();
    int updateQna(QnaDTO qnaDTO);
    int deleteQna(Long qnaNo);
}
