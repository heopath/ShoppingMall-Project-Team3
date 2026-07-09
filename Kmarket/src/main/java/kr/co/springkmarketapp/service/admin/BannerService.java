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

    @Value("${file.upload-dir}")
    private String uploadDir; // application.yml 기준: uploads

    /**
     * 실제 파일 저장 폴더
     * 예: C:\Users\GGG\Desktop\새 폴더\ShoppingMall-Project-Team3\Kmarket/uploads\banner
     */
    private File getBannerUploadFolder() {
        File folder = new File(System.getProperty("user.dir"), uploadDir + File.separator + "banner");

        if (!folder.exists()) {
            boolean created = folder.mkdirs();
            log.info(">>> 배너 업로드 폴더 생성 여부: {}, 경로: {}", created, folder.getAbsolutePath());
        }

        return folder;
    }

    /**
     * 배너 이미지 저장
     * DB에는 웹 접근 경로인 /uploads/banner/파일명 으로 저장
     */
    private String saveBannerImage(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new RuntimeException("배너 이미지는 필수입니다.");
        }

        File folder = getBannerUploadFolder();

        String originalFilename = file.getOriginalFilename();

        if (originalFilename == null || originalFilename.isBlank()) {
            throw new RuntimeException("파일명이 올바르지 않습니다.");
        }

        String savedFileName = UUID.randomUUID() + "_" + originalFilename;
        File saveFile = new File(folder, savedFileName);

        try {
            file.transferTo(saveFile);
            log.info(">>> 배너 이미지 저장 성공: {}", saveFile.getAbsolutePath());
        } catch (IOException e) {
            log.error("파일 저장 실패: {}", saveFile.getAbsolutePath(), e);
            throw new RuntimeException("파일 저장 중 오류가 발생했습니다.");
        }

        return "/uploads/banner/" + savedFileName;
    }

    @Transactional
    public int insertBanner(BannerDTO bannerDTO) {
        MultipartFile file = bannerDTO.getBannerFile();

        String imagePath = saveBannerImage(file);
        bannerDTO.setImagePath(imagePath);

        if (bannerDTO.getUseYn() == null) {
            bannerDTO.setUseYn("Y");
        }

        if (bannerDTO.getSortOrder() == null) {
            bannerDTO.setSortOrder(0);
        }

        return bannerDAO.insertBanner(bannerDTO);
    }

    @Transactional
    public int modifyBanner(BannerDTO bannerDTO) {
        MultipartFile file = bannerDTO.getBannerFile();

        BannerDTO existing = bannerDAO.selectBanner(bannerDTO.getBannerNo());

        if (existing == null) {
            throw new RuntimeException("수정할 배너 정보를 찾을 수 없습니다.");
        }

        if (file != null && !file.isEmpty()) {
            // 새 파일이 있으면 기존 파일 삭제 후 새 파일 저장
            deleteImageFile(existing.getImagePath());

            String imagePath = saveBannerImage(file);
            bannerDTO.setImagePath(imagePath);
        } else {
            // 새 파일이 없으면 기존 이미지 유지
            bannerDTO.setImagePath(existing.getImagePath());
        }

        if (bannerDTO.getUseYn() == null) {
            bannerDTO.setUseYn(existing.getUseYn());
        }

        if (bannerDTO.getSortOrder() == null) {
            bannerDTO.setSortOrder(existing.getSortOrder());
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

    /**
     * DB imagePath 예:
     * /uploads/banner/abc.png
     *
     * 실제 삭제 경로:
     * 프로젝트폴더/uploads/banner/abc.png
     */
    private void deleteImageFile(String imagePath) {
        if (imagePath == null || imagePath.isBlank()) {
            return;
        }

        try {
            String fileName = imagePath.substring(imagePath.lastIndexOf("/") + 1);
            File folder = getBannerUploadFolder();
            File file = new File(folder, fileName);

            if (file.exists()) {
                boolean deleted = file.delete();
                log.info(">>> 배너 이미지 파일 삭제 {} : {}", deleted ? "성공" : "실패", file.getAbsolutePath());
            } else {
                log.warn(">>> 삭제할 배너 이미지 파일이 존재하지 않음: {}", file.getAbsolutePath());
            }
        } catch (Exception e) {
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