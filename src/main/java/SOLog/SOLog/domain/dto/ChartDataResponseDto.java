package SOLog.SOLog.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ChartDataResponseDto {
    private String companyName;
    private String date;
    private Long open;
    private Long close;
    private Long high;
    private Long low;
    private Long volume;



}
