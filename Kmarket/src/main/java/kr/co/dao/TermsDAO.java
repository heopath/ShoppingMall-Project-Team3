package kr.co.dao;

import kr.co.sboard.dto.TermsDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TermsDAO {

    public void insert(TermsDTO dto);

    public TermsDTO select(int no);

    public List<TermsDTO> selectAll();

    public void update(TermsDTO dto);

    public void delete(int no);

}