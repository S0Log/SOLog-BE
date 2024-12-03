package SOLog.SOLog.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class TrendMatchChartResponseDto {
    private List<ChartDataResponseDto> baseData;//그 기간 baseData
    private ArrayList<List<ChartDataResponseDto>> similarDataList;// 비슷한거 여러개 차트
    private List<Date> markingDateList;
    private Date baseDate;
    private Long highlightNum;

}
