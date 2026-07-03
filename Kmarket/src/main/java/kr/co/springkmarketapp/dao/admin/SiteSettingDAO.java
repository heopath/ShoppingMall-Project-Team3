package kr.co.springkmarketapp.dao.admin;

import kr.co.springkmarketapp.dto.admin.SiteSettingDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SiteSettingDAO {
    int insertSiteSetting(SiteSettingDTO siteSettingDTO);
    SiteSettingDTO selectSiteSetting(Integer settingNo);
    List<SiteSettingDTO> selectSiteSettingList();
    int updateSiteSetting(SiteSettingDTO siteSettingDTO);
    int deleteSiteSetting(Integer settingNo);
}
