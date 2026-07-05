package kr.co.springkmarketapp.repository.company;

import kr.co.springkmarketapp.entity.company.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruitRepository extends JpaRepository<Recruit, Integer> {
}
