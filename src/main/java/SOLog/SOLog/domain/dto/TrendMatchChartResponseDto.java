package SOLog.SOLog.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class TrendMatchChartResponseDto {
    private List<ChartDataResponseDto>[] chartDataResponseDto;
    private Date markingDate;
    private Date baseDate;
    private Long highlightNum;

}
