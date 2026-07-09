package kr.co.springkmarketapp.repository.admin;

import kr.co.springkmarketapp.entity.admin.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BannerRepository extends JpaRepository<Banner, Integer> {
    // 배너 등록순 혹은 정렬 순서대로 전체 목록 조회
    List<Banner> findAllByOrderBySortOrderAscRegDateDesc();
}
