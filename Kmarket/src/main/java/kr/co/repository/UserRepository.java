package kr.co.repository;

import kr.co.sboard.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    int countByUserid(String userid);
    int countByNick(String nick);
    int countByEmail(String email);
    int countByHp(String hp);
}