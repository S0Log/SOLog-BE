package SOLog.SOLog.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MarketShareDto {
    private String mainProduct;
    private String sharePercent;
}
