package kr.co.springkmarketapp.repository.cs;

import kr.co.springkmarketapp.entity.cs.Faq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FaqRepository extends JpaRepository<Faq, Integer> {
}
