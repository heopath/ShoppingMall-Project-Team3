package kr.co.service;

import kr.co.sboard.dao.FileDAO;
import kr.co.sboard.dto.ArticleDTO;
import kr.co.sboard.dto.FileDTO;
import kr.co.sboard.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Log4j2
@RequiredArgsConstructor
@Service
public class FileService {

    private final FileDAO dao;
    private final FileRepository repository;

    @Value("${spring.servlet.multipart.location}")
    private String fileUploadPath;

    public List<FileDTO> upload(ArticleDTO articleDTO){

        File fileUploadDir = new File(fileUploadPath);

        if(!fileUploadDir.exists()){
            fileUploadDir.mkdirs();
        }

        String path = fileUploadDir.getAbsolutePath();

        // 반환용 파일 리스트
        List<FileDTO> fileList = new ArrayList<>();

        for(MultipartFile multiFile : articleDTO.getFiles()){

            // 파일을 첨부 했으면
            if(!multiFile.isEmpty()){
                String ofname = multiFile.getOriginalFilename();
                String ext = ofname.substring(ofname.lastIndexOf("."));
                String sfname = UUID.randomUUID().toString() + ext;

                try {
                    // 파일 저장
                    multiFile.transferTo(new File(path, sfname));

                    // 반환용 파일 객체
                    FileDTO fileDTO = FileDTO.builder()
                            .ofname(ofname)
                            .sfname(sfname)
                            .build();

                    // 리스트 추가
                    fileList.add(fileDTO);

                }catch (Exception e) {
                    log.error(e.getMessage());
                } // try-catch 끝
            } // if 끝
        } // for 끝

        return fileList;
    }

    public void download(){

    }

    public FileDTO get(int fno){
        return null;
    }
    public List<FileDTO> getAll(){
        return null;
    }
    public void register(FileDTO dto){

    }
    public void modify(FileDTO dto){

    }
    public void remove(int fno){

    }
}