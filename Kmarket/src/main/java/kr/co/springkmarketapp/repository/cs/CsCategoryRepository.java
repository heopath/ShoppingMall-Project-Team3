package kr.co.springkmarketapp.repository.cs;

import kr.co.springkmarketapp.entity.cs.CsCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CsCategoryRepository extends JpaRepository<CsCategory, Integer> {
}
