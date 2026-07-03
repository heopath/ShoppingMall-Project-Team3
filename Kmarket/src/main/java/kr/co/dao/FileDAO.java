package kr.co.dao;

import kr.co.sboard.dto.FileDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileDAO {

    public void insert(FileDTO dto);

    public FileDTO select(int fno);

    public List<FileDTO> selectAll();

    public void update(FileDTO dto);

    public void delete(int fno);

}