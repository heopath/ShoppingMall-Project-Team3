package kr.co.springkmarketapp.entity.cs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "qna")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Qna {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qna_no")
    private Long qnaNo;
    @Column(name = "member_no")
    private Integer memberNo;
    @Column(name = "cs_cate_no")
    private Integer csCateNo;
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "answer")
    private String answer;
    @Column(name = "answer_member_no")
    private Integer answerMemberNo;
    @Column(name = "status")
    private String status;
    @Column(name = "reg_date")
    private LocalDateTime regDate;
    @Column(name = "answer_date")
    private LocalDateTime answerDate;
}
