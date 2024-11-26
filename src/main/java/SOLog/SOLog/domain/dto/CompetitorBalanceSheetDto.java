package SOLog.SOLog.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompetitorBalanceSheetDto {
    private String companyName;
    private Double totalEquity;
    private Double totalLiabilities;
    private String operIncome;
}
