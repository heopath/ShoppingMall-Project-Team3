package kr.co.springkmarketapp.service.admin;

import kr.co.springkmarketapp.dao.admin.AppVersionDAO;
import kr.co.springkmarketapp.dto.admin.AppVersionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppVersionService {

    private final AppVersionDAO appVersionDAO;

    public int insertAppVersion(AppVersionDTO appVersionDTO) {
        return appVersionDAO.insertAppVersion(appVersionDTO);
    }

    public AppVersionDTO selectAppVersion(Integer versionNo) {
        return appVersionDAO.selectAppVersion(versionNo);
    }

    public List<AppVersionDTO> selectAppVersionList() {
        return appVersionDAO.selectAppVersionList();
    }

    public int updateAppVersion(AppVersionDTO appVersionDTO) {
        return appVersionDAO.updateAppVersion(appVersionDTO);
    }

    public int deleteAppVersion(Integer versionNo) {
        return appVersionDAO.deleteAppVersion(versionNo);
    }
}
