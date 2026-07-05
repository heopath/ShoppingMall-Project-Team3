package kr.co.springkmarketapp.dao.admin;

import kr.co.springkmarketapp.dto.admin.AppVersionDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AppVersionDAO {
    int insertAppVersion(AppVersionDTO appVersionDTO);
    AppVersionDTO selectAppVersion(Integer versionNo);
    List<AppVersionDTO> selectAppVersionList();
    int updateAppVersion(AppVersionDTO appVersionDTO);
    int deleteAppVersion(Integer versionNo);
}
