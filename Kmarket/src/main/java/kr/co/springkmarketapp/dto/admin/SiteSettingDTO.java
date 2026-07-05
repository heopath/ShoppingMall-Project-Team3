package kr.co.springkmarketapp.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SiteSettingDTO {
    private Integer settingNo;
    private String settingKey;
    private String settingValue;
    private LocalDateTime updateDate;
}
