package SOLog.SOLog.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompetitorProfitabilityDto {
    private String companyName;
    private String ROE;
    private String operatingMargin;
    private String revenueGrowthRate;
}
