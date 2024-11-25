package SOLog.SOLog.service;

import SOLog.SOLog.domain.dto.CompanyOverviewDto;
import SOLog.SOLog.domain.dto.MarketShareDto;
import SOLog.SOLog.domain.entity.CompanyEntity;
import SOLog.SOLog.domain.entity.MarketShareEntity;
import SOLog.SOLog.repository.CompanyInfoRepository;
import SOLog.SOLog.repository.MarketShareRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyInfoService {
    private final CompanyInfoRepository companyInfoRepository;
    private final MarketShareRepository marketShareRepository;

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
}
