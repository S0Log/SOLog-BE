package SOLog.SOLog.service;

import SOLog.SOLog.domain.dto.CompetitorPriceDto;
import SOLog.SOLog.domain.entity.CompanyFinancialEntity;
import SOLog.SOLog.repository.CompanyFinancialRepository;
import SOLog.SOLog.repository.RelationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompetitorInfoService {
    private final CompanyFinancialRepository companyFinancialRepository;
    private final RelationRepository relationRepository;

    public List<CompetitorPriceDto> getCompetitorPrice(String companyName) {

        List<String> competitorCompanies = relationRepository.findCompetitorNamesByCompanyName(companyName);
        competitorCompanies.add(0, companyName);

        List<CompetitorPriceDto> competitorPriceDtos = new ArrayList<>();

        // 경쟁사 리스트 순회하여 금융 데이터 가져오기
        for (String company : competitorCompanies) {
            CompanyFinancialEntity financialEntity = companyFinancialRepository.findByCompanyName(company);
            if (financialEntity != null) {
                competitorPriceDtos.add(
                        new CompetitorPriceDto(company, financialEntity.getMarketCapital())
                );
            }
        }

        return competitorPriceDtos;
    }
}
