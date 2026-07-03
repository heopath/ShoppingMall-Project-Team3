package kr.co.springkmarketapp.service.cs;

import kr.co.springkmarketapp.dao.cs.FaqDAO;
import kr.co.springkmarketapp.dto.cs.FaqDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FaqService {

    private final FaqDAO faqDAO;

    public int insertFaq(FaqDTO faqDTO) {
        return faqDAO.insertFaq(faqDTO);
    }

    public FaqDTO selectFaq(Integer faqNo) {
        return faqDAO.selectFaq(faqNo);
    }

    public List<FaqDTO> selectFaqList() {
        return faqDAO.selectFaqList();
    }

    public int updateFaq(FaqDTO faqDTO) {
        return faqDAO.updateFaq(faqDTO);
    }

    public int deleteFaq(Integer faqNo) {
        return faqDAO.deleteFaq(faqNo);
    }
}
