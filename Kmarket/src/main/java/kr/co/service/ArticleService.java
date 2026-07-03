package kr.co.service;

import kr.co.sboard.dao.ArticleDAO;
import kr.co.sboard.dto.ArticleDTO;
import kr.co.sboard.dto.PageRequestDTO;
import kr.co.sboard.entity.Article;
import kr.co.sboard.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleDAO dao;
    private final ArticleRepository repository;

    public ArticleDTO get(int ano){
        return null;
    }
    public int getLastPageNum(int total) {

        // 마지막 페이지 번호
        int lastPageNum = 0;

        if(total % 10 == 0){
            lastPageNum = total / 10;
        }else {
            lastPageNum = total / 10 + 1;
        }

        return lastPageNum;
    }

    public int getStart(int page){
        // 목록 LIMIT 시작값
        int start = (page - 1) * 10;
        return start;
    }

    public int getPageGroupStart(int page){
        return ((page - 1) / 10) * 10 + 1;
    }

    public int getPageGroupEnd(int page, int lastPageNum){
        int end = getPageGroupStart(page) + 9;
        if(end > lastPageNum){
            end = lastPageNum;
        }
        return end;
    }


    public int getTotal(){
        return dao.selectCountAll();
    }

    public List<ArticleDTO> getAll(int start){

        // Mybatis
        List<ArticleDTO> dtoList = dao.selectAll(start);
        return dtoList;
    }

    public List<ArticleDTO> findAll(PageRequestDTO pageRequestDTO){

        // Pageable은 JPA에서 페이징 처리를 위한 객체
        Pageable pageable = pageRequestDTO.getPageable("ano");

        Page<Article> pageArticle = repository.findAll(pageable);

        List<ArticleDTO> dtoList = pageArticle.getContent()
                .stream()
                .map(entity -> entity.toDTO())
                .toList();

        return dtoList;
    }

    public void register(ArticleDTO dto){
        dao.insert(dto);
    }

    public void modify(ArticleDTO dto){

    }
    public void remove(int ano){

    }
}