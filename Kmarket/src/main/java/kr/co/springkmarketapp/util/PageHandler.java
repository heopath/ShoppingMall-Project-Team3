package kr.co.springkmarketapp.util;

import lombok.Getter;

@Getter
public class PageHandler {

    private int currentPage;
    private int pageSize;
    private int total;

    private int lastPage;
    private int startPage;
    private int endPage;

    public PageHandler(int currentPage, int total, int pageSize) {

        this.currentPage = currentPage;
        this.total = total;
        this.pageSize = pageSize;

        lastPage = (int)Math.ceil((double) total / pageSize);

        startPage = ((currentPage - 1) / 10) * 10 + 1;
        endPage = startPage + 9;

        if(endPage > lastPage){
            endPage = lastPage;
        }
    }

    // 현재 페이지에서 몇 번째 데이터부터 가져와야 하는지 계산
    public int getOffset(){
        return (currentPage - 1) * pageSize;
    }

}