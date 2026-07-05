package kr.co.springkmarketapp.dao.cs;

import kr.co.springkmarketapp.dto.cs.FaqDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FaqDAO {
    int insertFaq(FaqDTO faqDTO);
    FaqDTO selectFaq(Integer faqNo);
    List<FaqDTO> selectFaqList();
    int updateFaq(FaqDTO faqDTO);
    int deleteFaq(Integer faqNo);
}
