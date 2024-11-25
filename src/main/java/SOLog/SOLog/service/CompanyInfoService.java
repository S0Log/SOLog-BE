package SOLog.SOLog.service;

import SOLog.SOLog.domain.dto.CompanyOverviewDto;
import SOLog.SOLog.domain.entity.CompanyEntity;
import SOLog.SOLog.repository.CompanyInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyInfoService {
    private final CompanyInfoRepository companyInfoRepository;

    public CompanyOverviewDto getCompanyOverview(String companyName) {
        CompanyEntity company = companyInfoRepository.findById(companyName)
                .orElseThrow(() -> new IllegalArgumentException("Company not found with name: " + companyName));

        return new CompanyOverviewDto(company.getOverviewDate(), company.getOverview());
    }
}
