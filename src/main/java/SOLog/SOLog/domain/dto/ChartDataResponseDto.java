package SOLog.SOLog.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ChartDataResponseDto {
    private String companyName;
    private Date date;
    private Long openPrice;
    private Long closePrice;
    private Long highPrice;
    private Long lowPrice;
    private Long volume;



}
