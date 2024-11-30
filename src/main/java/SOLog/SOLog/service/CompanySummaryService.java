package SOLog.SOLog.service;

import SOLog.SOLog.domain.dto.CompanySummaryDto;
import SOLog.SOLog.domain.entity.CompanyFinancialEntity;
import SOLog.SOLog.domain.entity.StockDataEntity;
import SOLog.SOLog.repository.CompanyFinancialRepository;
import SOLog.SOLog.repository.StockDataRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class CompanySummaryService {
    private final StockDataRepository stockDataRepository;

    public CompanySummaryDto getCompanySummary(String companyName, Date date, String durationType) {

        StockDataEntity stockData = stockDataRepository.findByCompanyNameAndDateByDay(companyName, date, durationType);
        if (stockData == null) {
            throw new EntityNotFoundException("No stock data found for the given date.");
        }

        return new CompanySummaryDto(
                companyName,
                date,
                stockData.getVolume(),
                stockData.getHighPrice(),
                stockData.getLowPrice(),
                stockData.getOpenPrice(),
                stockData.getClosePrice()
        );
    }
}
