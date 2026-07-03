package kr.co.springkmarketapp.repository.admin;

import kr.co.springkmarketapp.entity.admin.SiteSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteSettingRepository extends JpaRepository<SiteSetting, Integer> {
}
