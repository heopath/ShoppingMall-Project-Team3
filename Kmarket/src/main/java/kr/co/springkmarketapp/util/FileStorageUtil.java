package kr.co.springkmarketapp.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Component
public class FileStorageUtil {

    @Value("${file.upload-dir}")
    private String uploadDir;   // src/main/resources/static/uploads

    /**
     * @param file   업로드 파일
     * @param subDir 저장 하위 경로 (예: product/thumb1, product/detail)
     * @return DB 저장 및 화면 접근용 경로 (예: /uploads/product/thumb1/uuid.jpg)
     */
    public String saveFile(MultipartFile file, String subDir) {
        try {
            String originalName = file.getOriginalFilename();
            String ext = originalName.substring(originalName.lastIndexOf("."));
            String newName = UUID.randomUUID() + ext;

            Path targetDir = Paths.get(uploadDir, subDir);
            Files.createDirectories(targetDir);
            file.transferTo(targetDir.resolve(newName));

            return "/uploads/" + subDir + "/" + newName;
        } catch (IOException e) {
            throw new RuntimeException("파일 저장 실패: " + file.getOriginalFilename(), e);
        }
    }
}