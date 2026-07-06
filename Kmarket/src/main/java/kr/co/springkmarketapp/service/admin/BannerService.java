package kr.co.springkmarketapp.service.admin;

import kr.co.springkmarketapp.dao.admin.BannerDAO;
import kr.co.springkmarketapp.dto.admin.BannerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BannerService {

    private final BannerDAO bannerDAO;

    public int insertBanner(BannerDTO bannerDTO) {
        return bannerDAO.insertBanner(bannerDTO);
    }

    public BannerDTO selectBanner(Integer bannerNo) {
        return bannerDAO.selectBanner(bannerNo);
    }

    public List<BannerDTO> selectBannerList() {
        return bannerDAO.selectBannerList();
    }

    public int updateBanner(BannerDTO bannerDTO) {
        return bannerDAO.updateBanner(bannerDTO);
    }

    public int deleteBanner(Integer bannerNo) {
        return bannerDAO.deleteBanner(bannerNo);
    }

    public BannerDTO getTopBanner() {
        return bannerDAO.selectTopBanner();
    }

    public List<BannerDTO> getMain2Banners() {
        return bannerDAO.selectMain2Banners();
    }
}
