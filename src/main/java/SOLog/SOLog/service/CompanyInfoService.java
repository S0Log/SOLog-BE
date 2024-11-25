package SOLog.SOLog.service;

import SOLog.SOLog.domain.dto.CompanyOverviewDto;
import SOLog.SOLog.domain.dto.MarketShareDto;
import SOLog.SOLog.domain.dto.SalesTrendRatioDto;
import SOLog.SOLog.domain.entity.CompanyEntity;
import SOLog.SOLog.domain.entity.MarketShareEntity;
import SOLog.SOLog.domain.entity.SalesTrendRatioEntity;
import SOLog.SOLog.repository.CompanyInfoRepository;
import SOLog.SOLog.repository.MarketShareRepository;
import SOLog.SOLog.repository.SalesTrendRatioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyInfoService {
    private final CompanyInfoRepository companyInfoRepository;
    private final MarketShareRepository marketShareRepository;
    private final SalesTrendRatioRepository salesTrendRatioRepository;

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
                .map(entity -> new SalesTrendRatioDto(entity.getDate(), entity.getProductName(), entity.getSalesPercent()))
                .collect(Collectors.toList());
    }
}
