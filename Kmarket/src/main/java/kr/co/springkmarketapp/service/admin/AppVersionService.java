package kr.co.springkmarketapp.service.admin;

import kr.co.springkmarketapp.entity.AppVersion;
import kr.co.springkmarketapp.repository.admin.AppVersionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppVersionService {

    private final AppVersionRepository appVersionRepository;

    // 전체 버전 로그 목록 조회
    public List<AppVersion> selectVersionList() {
        return appVersionRepository.findAll();
    }

    // 최신 버전 정보 가져오기
    public AppVersion getLatestVersion() {
        return appVersionRepository.findFirstByOrderByVersionNoDesc().orElse(null);
    }

    // 신규 버전 및 로그 등록
    public AppVersion insertVersion(AppVersion appVersion) {
        // 임시로 writerNo 설정 (예: 1번 최고관리자)
        if(appVersion.getWriterNo() == null) {
            appVersion.setWriterNo(1);
        }
        return appVersionRepository.save(appVersion);
    }

    // 삭제
    @org.springframework.transaction.annotation.Transactional
    public void deleteVersions(List<Integer> versionNos) {
        appVersionRepository.deleteAllByIdInBatch(versionNos);
    }

    // 수정
    @org.springframework.transaction.annotation.Transactional
    public void updateVersion(AppVersion form) {
        AppVersion version = appVersionRepository.findById(form.getVersionNo()).orElse(null);
        if(version != null) {
            version.setVersionName(form.getVersionName());
            version.setChangeLog(form.getChangeLog());
        }
    }

    // JpaRepository 페이징 기능
    public Page<AppVersion> selectVersionPage(Pageable pageable) {
        return appVersionRepository.findAll(pageable);
    }
}