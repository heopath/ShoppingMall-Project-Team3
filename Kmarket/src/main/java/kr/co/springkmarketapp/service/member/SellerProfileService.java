package kr.co.springkmarketapp.service.member;

import kr.co.springkmarketapp.dao.member.SellerProfileDAO;
import kr.co.springkmarketapp.dto.member.SellerProfileDTO;
import kr.co.springkmarketapp.dto.member.SellerSalesDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SellerProfileService {

    private final SellerProfileDAO sellerProfileDAO;

    public SellerProfileDTO selectSellerProfile(Integer sellerNo) {
        return sellerProfileDAO.selectSellerProfile(sellerNo);
    }

    public SellerProfileDTO selectSellerProfileByMemberNo(Integer memberNo) {
        return sellerProfileDAO.selectSellerProfileByMemberNo(memberNo);
    }

    public List<SellerProfileDTO> selectSellerProfileList() {
        return sellerProfileDAO.selectSellerProfileList();
    }

    public List<SellerProfileDTO> selectSellerProfileList(String searchType, String keyword) {
        return sellerProfileDAO.selectSellerProfileListBySearch(searchType, keyword);
    }

    public int updateSellerProfile(SellerProfileDTO sellerProfileDTO) {
        return sellerProfileDAO.updateSellerProfile(sellerProfileDTO);
    }

    public int deleteSellerProfile(Integer sellerNo) {
        return sellerProfileDAO.deleteSellerProfile(sellerNo);
    }

    // 페이지네이션
    public List<SellerProfileDTO> selectSellerProfileList(int offset, int limit) {
        return sellerProfileDAO.selectSellerProfileListWithPaging(offset, limit);
    }

    public List<SellerProfileDTO> selectSellerProfileList(String searchType, String keyword, int offset, int limit) {
        return sellerProfileDAO.selectSellerProfileListBySearchWithPaging(searchType, keyword, offset, limit);
    }

    public int countSellerProfileList() {
        return sellerProfileDAO.countSellerProfileList();
    }

    public int countSellerProfileList(String searchType, String keyword) {
        return sellerProfileDAO.countSellerProfileListBySearch(searchType, keyword);
    }

    public int updateSellerStatus(int sellerNo, String status) {
        return sellerProfileDAO.updateSellerStatus(sellerNo, status);
    }

    @Transactional
    public void insertSellerProfile(SellerProfileDTO dto) {

        Integer memberNo = sellerProfileDAO.selectMemberNoByMemberId(dto.getMemberId());

        if (memberNo == null) {
            throw new IllegalArgumentException("존재하지 않는 회원 아이디입니다.");
        }

        if (sellerProfileDAO.countSellerByMemberNo(memberNo) > 0) {
            throw new IllegalArgumentException("이미 상점이 등록된 회원입니다.");
        }

        if (sellerProfileDAO.countBizNo(dto.getBizNo()) > 0) {
            throw new IllegalArgumentException("이미 등록된 사업자등록번호입니다.");
        }

        dto.setMemberNo(memberNo);
        sellerProfileDAO.insertSellerProfile(dto);
    }

    @Transactional
    public void deleteSellerProfiles(List<Integer> sellerNos) {
        sellerProfileDAO.deleteSellerProfiles(sellerNos);
    }


    public List<SellerSalesDTO> selectSellerSalesList(String salesPeriod, int offset, int limit) {
        return sellerProfileDAO.selectSellerSalesList(salesPeriod, offset, limit);
    }

    public int countSellerSalesList(String salesPeriod) {
        return sellerProfileDAO.countSellerSalesList(salesPeriod);
    }

    public int countBizNo(String bizNo) {
        return sellerProfileDAO.countBizNo(bizNo);
    }

    public int countCompanyName(String companyName){
        return sellerProfileDAO.countCompanyName(companyName);
    }

    public int countOnlineSaleNo(String onlineSaleNo){
        return sellerProfileDAO.countOnlineSaleNo(onlineSaleNo);
    }

    public int countTel(String tel){
        return sellerProfileDAO.countTel(tel);
    }
}
