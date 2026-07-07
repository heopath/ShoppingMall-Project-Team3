package kr.co.springkmarketapp.repository.admin;

import kr.co.springkmarketapp.entity.AppVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AppVersionRepository extends JpaRepository<AppVersion, Integer> {
    // 가장 최근에 등록된 버전 정보를 한 건 조회
    Optional<AppVersion> findFirstByOrderByVersionNoDesc();
}