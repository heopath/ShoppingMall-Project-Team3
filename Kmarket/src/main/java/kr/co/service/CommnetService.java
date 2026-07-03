package kr.co.service;

import kr.co.sboard.dao.CommentDAO;
import kr.co.sboard.dto.CommentDTO;
import kr.co.sboard.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Service
public class CommnetService {
    private final CommentDAO dao;
    private final CommentRepository repository;

    public CommentDTO get(int cno){
        return null;
    }
    public List<CommentDTO> getAll(){
        return null;
    }
    public void register(CommentDTO dto){

    }
    public void modify(CommentDTO dto){
        
    }
    public void remove(int cno){

    }

}
