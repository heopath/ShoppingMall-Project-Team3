package kr.co.dao;

import kr.co.sboard.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDAO {

    public void insert(UserDTO dto);

    public UserDTO select(String userid);

    public List<UserDTO> selectAll();

    public void update(UserDTO dto);

    public void delete(int userid);

}