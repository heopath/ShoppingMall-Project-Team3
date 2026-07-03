package kr.co.dto;

import kr.co.sboard.entity.Terms;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TermsDTO {

    private int no;
    private String basic;
    private String privacy;

    public Terms toEntity(){
        return TermsDTO.builder()
                .no(no)
                .basic(basic)
                .privacy(privacy)
                .build().toEntity();
    }
}
