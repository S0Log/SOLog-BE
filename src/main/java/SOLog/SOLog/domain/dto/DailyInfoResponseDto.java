package SOLog.SOLog.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DailyInfoResponseDto {
    //private String date;
    private String companyNum;
    private String marketType;
    private String companyName;
    private Long yesterday;
    private Long diff;
    private Double percentage;
}
