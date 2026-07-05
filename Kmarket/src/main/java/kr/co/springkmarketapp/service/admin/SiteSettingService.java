package kr.co.springkmarketapp.service.admin;

import kr.co.springkmarketapp.dao.admin.SiteSettingDAO;
import kr.co.springkmarketapp.dto.admin.SiteSettingDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SiteSettingService {

    private final SiteSettingDAO siteSettingDAO;

    public int insertSiteSetting(SiteSettingDTO siteSettingDTO) {
        return siteSettingDAO.insertSiteSetting(siteSettingDTO);
    }

    public SiteSettingDTO selectSiteSetting(Integer settingNo) {
        return siteSettingDAO.selectSiteSetting(settingNo);
    }

    public List<SiteSettingDTO> selectSiteSettingList() {
        return siteSettingDAO.selectSiteSettingList();
    }

    public int updateSiteSetting(SiteSettingDTO siteSettingDTO) {
        return siteSettingDAO.updateSiteSetting(siteSettingDTO);
    }

    public int deleteSiteSetting(Integer settingNo) {
        return siteSettingDAO.deleteSiteSetting(settingNo);
    }
}
