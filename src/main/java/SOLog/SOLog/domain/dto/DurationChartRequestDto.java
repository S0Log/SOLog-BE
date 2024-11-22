package SOLog.SOLog.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DurationChartRequestDto {
    private String durationType;
    private String companyName;
}
