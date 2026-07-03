package kr.co.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PageResponseDTO {

    private List<ArticleDTO> dtoList;
    private int page;
    private int size;
    private int total;
    private String type;
    private int startNo;
    private int start, end;
    private boolean prev, next;

    @Builder
    public PageResponseDTO(PageRequestDTO pageRequestDTO, List<ArticleDTO> dtoList, int total){
        this.type = pageRequestDTO.getType();
        this.page = pageRequestDTO.getPage();
        this.size = pageRequestDTO.getSize();

        this.total = total;
        this.dtoList = dtoList;

        this.startNo = total - ((page - 1) * size);
        this.start = ((page - 1) / 10) * 10 + 1;
        this.end = this.start + 9;

        int last = (int) (Math.ceil(total / (double) size));
        this.end = Math.min(end, last);
        this.prev = this.start > 1;
        this.next = total > this.end * this.size;
    }
}
