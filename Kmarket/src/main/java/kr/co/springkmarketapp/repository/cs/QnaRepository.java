package kr.co.springkmarketapp.repository.cs;

import kr.co.springkmarketapp.entity.cs.Qna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QnaRepository extends JpaRepository<Qna, Long> {
}
