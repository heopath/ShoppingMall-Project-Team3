package kr.co.springkmarketapp.dto.common;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageRequestDTO {

    private int page = 1;      // 현재 페이지
    private int size = 10;     // 한 페이지 상품 수

    // 상품 목록 조회 조건
    private int cateNo;
    private String sort = "sold";

    // 검색어
    private String keyword;

    // 검색 상세 조건
    private Boolean searchName;
    private Boolean searchDesc;
    private Integer minPrice;
    private Integer maxPrice;

    // MyBatis LIMIT 시작 위치
    public int getOffset() {
        return (page - 1) * size;
    }
}
