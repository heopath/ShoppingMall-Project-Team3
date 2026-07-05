package kr.co.springkmarketapp.dao.member;

import kr.co.springkmarketapp.dto.member.SellerProfileDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SellerProfileDAO {
    int insertSellerProfile(SellerProfileDTO sellerProfileDTO);
    SellerProfileDTO selectSellerProfile(Integer sellerNo);
    List<SellerProfileDTO> selectSellerProfileList();
    int updateSellerProfile(SellerProfileDTO sellerProfileDTO);
    int deleteSellerProfile(Integer sellerNo);
}
