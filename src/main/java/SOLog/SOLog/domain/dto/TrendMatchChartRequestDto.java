package SOLog.SOLog.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
public class TrendMatchChartRequestDto {
    private String companyName;
    private String period;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date baseDate;
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private Date startDate;
}
