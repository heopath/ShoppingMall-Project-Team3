package kr.co.entity;

import jakarta.persistence.*;
import kr.co.sboard.dto.TermsDTO;
import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="Terms")
public class Terms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int no;
    private String basic;
    private String privacy;

    public TermsDTO toDTO(){
        return TermsDTO.builder()
                .no(no)
                .basic(basic)
                .privacy(privacy)
                .build();
    }
}
