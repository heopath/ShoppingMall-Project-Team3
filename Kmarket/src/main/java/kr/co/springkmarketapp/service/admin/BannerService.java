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
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class BannerService {

    private final BannerDAO bannerDAO;

    @Value("${spring.servlet.multipart.location}")
    private String uploadPath; // application.yml에 지정된 기본 저장소 절대 경로(src/main/resources/static/uploads)

    @Transactional // 파일 저장과 DB 삽입을 하나의 트랜잭션으로 묶습니다.
    public int insertBanner(BannerDTO bannerDTO) {
        MultipartFile file = bannerDTO.getBannerFile();

        if (file == null || file.isEmpty()) {
            throw new RuntimeException("배너 이미지는 필수입니다."); // 파일 없으면 저장 중단
        }

        // 1. 저장 경로를 확실하게 지정 (절대 경로 추천)
        String projectRoot = System.getProperty("user.dir");
        File folder = new File(projectRoot, "src/main/resources/static/uploads/banner");
        if (!folder.exists()) folder.mkdirs();

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