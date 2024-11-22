package SOLog.SOLog.service;

import SOLog.SOLog.domain.dto.ChartDataResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ChartService {
    List<ChartDataResponseDto> getCompanyStockData(String companyName,String durationType);
    List<ChartDataResponseDto> getCompanyStockDataSetStart(String companyName,String durationType);

}
