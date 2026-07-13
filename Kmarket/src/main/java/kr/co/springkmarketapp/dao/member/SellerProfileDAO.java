package kr.co.springkmarketapp.dao.member;

import kr.co.springkmarketapp.dto.member.SellerProfileDTO;
import kr.co.springkmarketapp.dto.member.SellerSalesDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SellerProfileDAO {

    int insertSellerProfile(SellerProfileDTO sellerProfileDTO);

    SellerProfileDTO selectSellerProfile(Integer sellerNo);

    SellerProfileDTO selectSellerProfileByMemberNo(@Param("memberNo") Integer memberNo);

    List<SellerProfileDTO> selectSellerProfileList();

    int updateSellerProfile(SellerProfileDTO sellerProfileDTO);

    int deleteSellerProfile(Integer sellerNo);

    List<SellerProfileDTO> selectSellerProfileListBySearch(@Param("searchType") String searchType,
                                                           @Param("keyword") String keyword);

    List<SellerProfileDTO> selectSellerProfileListWithPaging(@Param("offset") int offset,
                                                             @Param("limit") int limit);

    List<SellerProfileDTO> selectSellerProfileListBySearchWithPaging(@Param("searchType") String searchType,
                                                                     @Param("keyword") String keyword,
                                                                     @Param("offset") int offset,
                                                                     @Param("limit") int limit);

    int countSellerProfileList();

    int countSellerProfileListBySearch(@Param("searchType") String searchType,
                                       @Param("keyword") String keyword);

    int updateSellerStatus(@Param("sellerNo") int sellerNo,
                           @Param("status") String status);

    Integer selectMemberNoByMemberId(String memberId);

    int countSellerByMemberNo(Integer memberNo);

    int countBizNo(String bizNo);

    int deleteSellerProfiles(@Param("sellerNos") List<Integer> sellerNos);

    List<SellerSalesDTO> selectSellerSalesList(@Param("salesPeriod") String salesPeriod,
                                               @Param("offset") int offset,
                                               @Param("limit") int limit);

    int countSellerSalesList(@Param("salesPeriod") String salesPeriod);

    int countCompanyName(String companyName);

    int countOnlineSaleNo(String onlineSaleNo);

    int countTel(String tel);
}
