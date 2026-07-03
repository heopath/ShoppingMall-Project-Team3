package kr.co.entity;

import jakarta.persistence.*;
import kr.co.sboard.dto.FileDTO;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="File")
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fno;
    private int ano;
    private String ofanme;
    private String sfanme;
    private int download;
    @CreationTimestamp
    private LocalDateTime wdate;

    public FileDTO toDTO(){
        return FileDTO.builder()
                .fno(fno)
                .ano(ano)
                .ofname(ofanme)
                .sfname(sfanme)
                .download(download)
                .wdate(wdate.toString())
                .build();
    }
}
