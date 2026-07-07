package kr.co.springkmarketapp.dto.common;

import lombok.Getter;
import java.util.List;

@Getter
public class PageResponseDTO<T> {

    private final List<T> dtoList;
    private final PageRequestDTO pageRequestDTO;

    private final int total;
    private final int startNo;
    private final int endNo;
    private final int lastPage;

    private final boolean prev;
    private final boolean next;

    public PageResponseDTO(
            List<T> dtoList,
            PageRequestDTO pageRequestDTO,
            int total
    ) {
        this.dtoList = dtoList;
        this.pageRequestDTO = pageRequestDTO;
        this.total = total;

        int page = pageRequestDTO.getPage();
        int size = pageRequestDTO.getSize();

        this.lastPage = (int) Math.ceil((double) total / size);

        // 페이지 번호를 10개씩 표시
        int end = (int) (Math.ceil(page / 10.0) * 10);
        int start = end - 9;

        this.endNo = Math.min(end, lastPage);
        this.startNo = Math.max(start, 1);

        this.prev = startNo > 1;
        this.next = endNo < lastPage;
    }
}
