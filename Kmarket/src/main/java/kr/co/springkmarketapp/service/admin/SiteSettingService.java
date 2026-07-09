package kr.co.springkmarketapp.service.admin;

import kr.co.springkmarketapp.dao.admin.SiteSettingDAO;
import kr.co.springkmarketapp.dto.admin.SiteSettingDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.Map;
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

    private static final Map<String, String> DEFAULT_SETTINGS = new HashMap<>();

    static {
        // 고객센터 정보
        DEFAULT_SETTINGS.put("customer_tel", "02-1234-5678");
        DEFAULT_SETTINGS.put("customer_time", "평일 09:00 ~ 18:00");
        DEFAULT_SETTINGS.put("customer_email", "admin@kmarket.com");
        DEFAULT_SETTINGS.put("finance_manager", "1234-1234");

        // 푸터 정보
        DEFAULT_SETTINGS.put("footer_copyright", "Copyright@Kmarket. All rights reserved");

        // 기업 정보
        DEFAULT_SETTINGS.put("company_name", "Kmarket");
        DEFAULT_SETTINGS.put("company_ceo", "홍길동");
        DEFAULT_SETTINGS.put("company_biz_no", "123-45-67890");
        DEFAULT_SETTINGS.put("company_sale_no", "2026-부산-0001");
        DEFAULT_SETTINGS.put("company_addr", "부산광역시");

        // 사이트 정보
        DEFAULT_SETTINGS.put("site_title", "케이마켓");
        DEFAULT_SETTINGS.put("site_subtitle", "더 나은 쇼핑의 시작");


        // 로고 정보
        DEFAULT_SETTINGS.put("header_logo", "");
        DEFAULT_SETTINGS.put("footer_logo", "");
        DEFAULT_SETTINGS.put("favicon", "");
    }


    //관리자-기본설정 기본값 세팅 -  DB에 없으면 기본값 사용
    public Map<String, String> getSettingsMap() {
        Map<String, String> settings = new HashMap<>(DEFAULT_SETTINGS);

        List<SiteSettingDTO> siteSettings = siteSettingDAO.selectSiteSettingList();

        for (SiteSettingDTO siteSetting : siteSettings) {
            settings.put(siteSetting.getSettingKey(), siteSetting.getSettingValue());
        }

        return settings;
    }


  //customer_tel, customer_time 등 필요한 값만 저장
  // DB에 있으면 UPDATE
  // DB에 없으면 INSERT
    @Transactional
    public void saveSettings(Map<String, String> params) {
        for (String key : DEFAULT_SETTINGS.keySet()) {
            if (!params.containsKey(key)) {
                continue;
            }

            SiteSettingDTO siteSettingDTO = SiteSettingDTO.builder()
                    .settingKey(key)
                    .settingValue(params.get(key))
                    .build();

            siteSettingDAO.upsertSiteSetting(siteSettingDTO);
        }
    }

    @Transactional
    public void saveOneSetting(String key, String value) {
        SiteSettingDTO siteSettingDTO = SiteSettingDTO.builder()
                .settingKey(key)
                .settingValue(value)
                .build();

        siteSettingDAO.upsertSiteSetting(siteSettingDTO);
    }
}
