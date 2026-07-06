package kr.co.springkmarketapp.service.member;

import kr.co.springkmarketapp.dao.member.SellerProfileDAO;
import kr.co.springkmarketapp.dto.member.SellerProfileDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SellerProfileService {

    private final SellerProfileDAO sellerProfileDAO;

//    public int insertSellerProfile(SellerProfileDTO sellerProfileDTO) {
//
//        return sellerProfileDAO.insertSellerProfile(sellerProfileDTO);
//    }

    public SellerProfileDTO selectSellerProfile(Integer sellerNo) {
        return sellerProfileDAO.selectSellerProfile(sellerNo);
    }

    public List<SellerProfileDTO> selectSellerProfileList() {
        return sellerProfileDAO.selectSellerProfileList();
    }

    public int updateSellerProfile(SellerProfileDTO sellerProfileDTO) {
        return sellerProfileDAO.updateSellerProfile(sellerProfileDTO);
    }

    public int deleteSellerProfile(Integer sellerNo) {
        return sellerProfileDAO.deleteSellerProfile(sellerNo);
    }
}
