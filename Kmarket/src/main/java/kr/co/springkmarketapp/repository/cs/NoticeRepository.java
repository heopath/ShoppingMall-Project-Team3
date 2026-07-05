package kr.co.springkmarketapp.repository.cs;

import kr.co.springkmarketapp.entity.cs.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Integer> {
}
