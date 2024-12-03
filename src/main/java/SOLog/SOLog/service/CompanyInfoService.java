package SOLog.SOLog.service;

import SOLog.SOLog.domain.dto.CompanyOverviewDto;
import SOLog.SOLog.domain.dto.DailyInfoResponseDto;
import SOLog.SOLog.domain.dto.MarketShareDto;
import SOLog.SOLog.domain.dto.SalesTrendRatioDto;
import SOLog.SOLog.domain.entity.*;
import SOLog.SOLog.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyInfoService {
    private final CompanyInfoRepository companyInfoRepository;
    private final MarketShareRepository marketShareRepository;
    private final SalesTrendRatioRepository salesTrendRatioRepository;
    private final IdentifierRepository identifierRepository;
    private final StockDataRepository stockDataRepository;

    // 기업개요
    public CompanyOverviewDto getCompanyOverview(String companyName) {
        CompanyEntity company = companyInfoRepository.findById(companyName)
                .orElseThrow(() -> new IllegalArgumentException("Company not found with name: " + companyName));

        return new CompanyOverviewDto(company.getOverviewDate(), company.getOverview());
    }

    // 시장점유율
    public List<MarketShareDto> getMarketShare(String companyName) {
        List<MarketShareEntity> marketShare = marketShareRepository.findByCompanyName(companyName);

        return marketShare.stream()
                .map(entity -> new MarketShareDto(entity.getMainProduct(), entity.getSharePercent()))
                .collect(Collectors.toList());
    }

    // 매출비중추이
    public List<SalesTrendRatioDto> getSalesTrendRatio(String companyName) {
        Pageable pageable = PageRequest.of(0, 20);
        List<SalesTrendRatioEntity> salesTrendRatio = salesTrendRatioRepository.findTop5ByCompanyName(companyName, pageable).getContent();

        return salesTrendRatio.stream()
                .map(entity -> new SalesTrendRatioDto(entity.getDate(), entity.getProductName().replace("?", " "), entity.getSalesPercent()))
                .collect(Collectors.toList());
    }
    public DailyInfoResponseDto getDailyInfo(String companyName) {
        IdentifierEntity identifierEntity = identifierRepository.findByCompany_CompanyName(companyName);
        List<StockDataEntity> stockDataEntities = stockDataRepository.findTop2ByCompanyNameAndDurationType(companyName);
        Long priceminus1 = stockDataEntities.get(0).getClosePrice();
        Long priceminus2 = stockDataEntities.get(1).getClosePrice();

        DailyInfoResponseDto dailyInfoResponseDto = new DailyInfoResponseDto(identifierEntity.getCompanyNum(),"코스피",companyName,stockDataEntities.get(0).getClosePrice(),stockDataEntities.get(0).getClosePrice()-stockDataEntities.get(1).getClosePrice(),(priceminus1-priceminus2)/(double)priceminus2);


        return dailyInfoResponseDto;

    }

}
