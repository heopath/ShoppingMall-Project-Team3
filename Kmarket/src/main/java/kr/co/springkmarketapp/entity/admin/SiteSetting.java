package kr.co.springkmarketapp.entity.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "site_setting")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SiteSetting {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "setting_no")
    private Integer settingNo;
    @Column(name = "setting_key")
    private String settingKey;
    @Column(name = "setting_value")
    private String settingValue;
    @Column(name = "update_date")
    private LocalDateTime updateDate;
}
