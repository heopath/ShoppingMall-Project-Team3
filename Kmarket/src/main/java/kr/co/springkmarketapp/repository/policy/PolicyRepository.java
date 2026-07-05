package kr.co.springkmarketapp.repository.policy;

import kr.co.springkmarketapp.entity.policy.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Integer> {
}
