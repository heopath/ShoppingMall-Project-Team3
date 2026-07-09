package kr.co.springkmarketapp.service.admin;

import kr.co.springkmarketapp.dao.admin.BannerDAO;
import kr.co.springkmarketapp.dto.admin.BannerDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BannerService {

    private final BannerDAO bannerDAO;

    @Value("${spring.servlet.multipart.location}")
    private String uploadPath; // application.yml에 지정된 기본 저장소 절대 경로(src/main/resources/static/uploads)

    @Value("${file.upload-dir}")
    private String uploadDir;   // uploadPath 대신 이걸로 교체

    @Transactional // 파일 저장과 DB 삽입을 하나의 트랜잭션으로 묶습니다.
    public int insertBanner(BannerDTO bannerDTO) {
        MultipartFile file = bannerDTO.getBannerFile();

        if (file == null || file.isEmpty()) {
            throw new RuntimeException("배너 이미지는 필수입니다."); // 파일 없으면 저장 중단
        }

        // 1. 저장 경로를 확실하게 지정 (절대 경로 추천)
        File folder = new File(System.getProperty("user.dir"), uploadDir + "/banner");
        if (!folder.exists()) {
            folder.mkdirs();
        }

        // 2. 파일 저장
        String savedFileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        try {
            file.transferTo(new File(folder, savedFileName));
        } catch (IOException e) {
            log.error("파일 저장 실패: ", e);
            throw new RuntimeException("파일 저장 중 오류가 발생했습니다.");
        }

        // 3. DB 경로 매핑 (웹 접근 주소)
        bannerDTO.setImagePath("/uploads/banner/" + savedFileName);

        // 4. 나머지 데이터 세팅
        if (bannerDTO.getUseYn() == null) bannerDTO.setUseYn("Y");
        if (bannerDTO.getSortOrder() == null) bannerDTO.setSortOrder(0);

        return bannerDAO.insertBanner(bannerDTO);
    }

    @Transactional
    public int modifyBanner(BannerDTO bannerDTO) {
        MultipartFile file = bannerDTO.getBannerFile();

        if (file != null && !file.isEmpty()) {
            // 새 파일이 첨부된 경우 → 기존 파일 삭제 후 새 파일 저장
            BannerDTO existing = bannerDAO.selectBanner(bannerDTO.getBannerNo());
            if (existing != null) {
                deleteImageFile(existing.getImagePath());
            }

            File folder = new File(uploadDir, "banner");
            if (!folder.exists()) folder.mkdirs();

            String savedFileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            try {
                file.transferTo(new File(folder, savedFileName));
            } catch (IOException e) {
                log.error("파일 저장 실패: ", e);
                throw new RuntimeException("파일 저장 중 오류가 발생했습니다.");
            }
            bannerDTO.setImagePath("/uploads/banner/" + savedFileName);

        } else {
            // 파일을 새로 첨부하지 않은 경우 → 기존 imagePath 그대로 유지
            BannerDTO existing = bannerDAO.selectBanner(bannerDTO.getBannerNo());
            bannerDTO.setImagePath(existing.getImagePath());
        }

        return bannerDAO.updateBanner(bannerDTO);
    }

    public BannerDTO selectBanner(Integer bannerNo) {
        return bannerDAO.selectBanner(bannerNo);
    }

    public List<BannerDTO> selectBannerList() {
        return bannerDAO.selectBannerList();
    }

    public List<BannerDTO> selectBannerListByPosition(String bannerPosition) {
        return bannerDAO.selectBannerListByPosition(bannerPosition);
    }

    public int updateBanner(BannerDTO bannerDTO) {
        return bannerDAO.updateBanner(bannerDTO);
    }

    @Transactional
    public int deleteBanner(Integer bannerNo) {
        BannerDTO banner = bannerDAO.selectBanner(bannerNo);
        int result = bannerDAO.deleteBanner(bannerNo);

        if (result > 0 && banner != null) {
            deleteImageFile(banner.getImagePath());
        }

        return result;
    }

    @Transactional
    public void deleteBanners(List<Integer> bannerNos) {
        // 삭제 전에 파일 경로들을 먼저 조회
        List<String> imagePaths = bannerNos.stream()
                .map(bannerDAO::selectBanner)
                .filter(Objects::nonNull)
                .map(BannerDTO::getImagePath)
                .collect(Collectors.toList());

        int result = bannerDAO.deleteBanners(bannerNos);

        if (result > 0) {
            imagePaths.forEach(this::deleteImageFile);
        }
    }

    private void deleteImageFile(String imagePath) {
        if (imagePath == null || imagePath.isBlank()) return;

        try {
            // imagePath는 "/uploads/banner/파일명" 형태 → 앞의 "/uploads/" 를 제거해 실제 물리 경로로 변환
            String relativePath = imagePath.replaceFirst("^/uploads/", "");
            File file = new File(uploadDir, relativePath);

            if (file.exists()) {
                boolean deleted = file.delete();
                log.info(">>> 배너 이미지 파일 삭제 {} : {}", deleted ? "성공" : "실패", file.getAbsolutePath());
            } else {
                log.warn(">>> 삭제할 배너 이미지 파일이 존재하지 않음: {}", file.getAbsolutePath());
            }
        } catch (Exception e) {
            // 파일 삭제 실패해도 DB 삭제 자체는 이미 끝난 상태이므로, 로그만 남기고 예외를 던지지 않음
            log.error("배너 이미지 파일 삭제 중 오류 발생: {}", imagePath, e);
        }
    }

    public BannerDTO getTopBanner() {
        return bannerDAO.selectTopBanner();
    }

    public List<BannerDTO> getMain2Banners() {
        return bannerDAO.selectMain2Banners();
    }
}