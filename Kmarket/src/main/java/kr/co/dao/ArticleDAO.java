package kr.co.dao;

import kr.co.sboard.dto.ArticleDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleDAO {

    public void insert(ArticleDTO dto);

    public ArticleDTO select(int ano);

    public List<ArticleDTO> selectAll(int start);

    public int selectCountAll();

    public void update(ArticleDTO dto);

    public void delete(int ano);
}