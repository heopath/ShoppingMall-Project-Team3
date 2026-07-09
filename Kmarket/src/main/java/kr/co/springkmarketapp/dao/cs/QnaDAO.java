package kr.co.springkmarketapp.dao.cs;

import kr.co.springkmarketapp.dto.cs.QnaDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QnaDAO {
    int insertQna(QnaDTO qnaDTO);
    QnaDTO selectQna(Long qnaNo);
    // 고객센터 메인용
    List<QnaDTO> selectQnaList();

    // QnA 목록 페이징용
    List<QnaDTO> selectQnaListPage(
            @Param("parentNo") int parentNo,
            @Param("offset") int offset,
            @Param("size") int size
    );

    // 관리자페이지에서의 parent 1차,2차 카테고리 조회
    List<QnaDTO> selectAdminQnaList();
    int updateQna(QnaDTO qnaDTO);
    int deleteQna(Long qnaNo);

    int countQna(int parentNo);

}
