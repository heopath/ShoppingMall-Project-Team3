package kr.co.springkmarketapp.dao.admin;

import kr.co.springkmarketapp.dto.admin.BannerDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BannerDAO {
    int insertBanner(BannerDTO bannerDTO);
    BannerDTO selectBanner(Integer bannerNo);
    BannerDTO selectTopBanner();
    List<BannerDTO> selectMain2Banners();
    List<BannerDTO> selectBannerList();
    int updateBanner(BannerDTO bannerDTO);
    int deleteBanner(Integer bannerNo);

    List<BannerDTO> selectBannerListByPosition(String bannerPosition);
}
