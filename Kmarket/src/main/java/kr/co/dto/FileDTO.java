package kr.co.dto;

import kr.co.sboard.entity.File;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileDTO {
    private int fno;
    private int ano;
    private String ofname;
    private String sfname;
    private int download;
    private String wdate;

    public File toEntity(){
        return File.builder()
                .fno(fno)
                .ano(ano)
                .ofanme(ofname)
                .sfanme(sfname)
                .download(download)
                .build();
    }
}
