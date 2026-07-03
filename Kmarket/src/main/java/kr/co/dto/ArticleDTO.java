package kr.co.dto;

import kr.co.sboard.entity.Article;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleDTO {

    private int ano;
    private String type;
    private String title;
    private String content;
    private int comment;
    private int file;
    private int hit;
    private String writer;
    private String regip;
    private String wdate;

    // 추가필드
    private String nick;

    // 폼 업로드 파일 객체
    private MultipartFile file1;
    private MultipartFile file2;

    public List<MultipartFile> getFiles(){
        return List.of(file1, file2);
    }


    public Article toEntity(){
        return Article.builder()
                .ano(ano)
                .type(type)
                .title(title)
                .content(content)
                .comment(comment)
                .file(file)
                .hit(hit)
                .writer(writer)
                .regip(regip)
                .build();
    }
}