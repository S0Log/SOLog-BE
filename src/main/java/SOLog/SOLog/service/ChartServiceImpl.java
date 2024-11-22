package SOLog.SOLog.service;

import SOLog.SOLog.domain.dto.ChartDataResponseDto;
import SOLog.SOLog.domain.entity.StockDataEntity;
import SOLog.SOLog.repository.StockDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChartServiceImpl implements ChartService {
    private final StockDataRepository stockDataRepository;

    @Override
    public List<ChartDataResponseDto> getCompanyStockData(String companyName, String durationType) {
        // companyName과 durationType을 기준으로 데이터를 조회
        List<StockDataEntity> stockDataEntities = stockDataRepository.findByCompany_CompanyNameAndDurationType(companyName, durationType);

        // 조회된 데이터를 ChartDataResponseDto로 변환
        return stockDataEntities.stream()
                .sorted(Comparator.comparing(StockDataEntity::getDate))
                .map(stockDataEntity -> new ChartDataResponseDto(
                        stockDataEntity.getCompany().getCompanyName(),
                        stockDataEntity.getDate(),
                        stockDataEntity.getOpenPrice(),
                        stockDataEntity.getClosePrice(),
                        stockDataEntity.getHighPrice(),
                        stockDataEntity.getLowPrice(),
                        stockDataEntity.getVolume()
                ))
                .collect(Collectors.toList());
    }

    // 2017년 09월 01일 이후의 데이터만 반환
    @Override
    public List<ChartDataResponseDto> getCompanyStockDataSetStart(String companyName, String durationType) {
        // 2017년 09월 01일 이후 날짜를 기준으로 필터링
        Date startDate = new Date(117, 8, 1); // 2017년 09월 01일 (Java Date는 0-based 월을 사용)

        // companyName과 durationType을 기준으로 데이터를 조회
        List<StockDataEntity> stockDataEntities = stockDataRepository.findByCompany_CompanyNameAndDurationType(companyName, durationType);

        // 2017년 09월 01일 이후의 데이터만 필터링
        return stockDataEntities.stream()
                .filter(stockDataEntity -> stockDataEntity.getDate().after(startDate))  // 날짜가 2017년 09월 01일 이후인지 확인
                .sorted(Comparator.comparing(StockDataEntity::getDate))
                .map(stockDataEntity -> new ChartDataResponseDto(
                        stockDataEntity.getCompany().getCompanyName(),
                        stockDataEntity.getDate(),
                        stockDataEntity.getOpenPrice(),
                        stockDataEntity.getClosePrice(),
                        stockDataEntity.getHighPrice(),
                        stockDataEntity.getLowPrice(),
                        stockDataEntity.getVolume()
                ))
                .collect(Collectors.toList());
    }
}