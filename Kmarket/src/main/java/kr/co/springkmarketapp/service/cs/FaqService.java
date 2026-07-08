package kr.co.springkmarketapp.service.cs;

import kr.co.springkmarketapp.dao.cs.FaqDAO;
import kr.co.springkmarketapp.dto.cs.FaqDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public int countFaq(int parentNo) {
        return faqDAO.countFaq(parentNo);
    }

    public List<FaqDTO> selectFaqListByParent(int parentNo) {
        return faqDAO.selectFaqListByParent(parentNo);
    }

    public Map<String, List<FaqDTO>> selectFaqGroup(int parentNo){

        List<FaqDTO> list = faqDAO.selectFaqListByParent(parentNo);
        System.out.println(list);

        Map<String, List<FaqDTO>> result = new LinkedHashMap<>();

        for(FaqDTO faq : list){

            String cate = faq.getCateName();

            if(!result.containsKey(cate)){
                result.put(cate, new ArrayList<>());
            }

            result.get(cate).add(faq);
        }

        return result;
    }
}
