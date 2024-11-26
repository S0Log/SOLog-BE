package SOLog.SOLog.service;

import SOLog.SOLog.domain.dto.CompetitorBalanceSheetDto;
import SOLog.SOLog.domain.dto.CompetitorPriceDto;
import SOLog.SOLog.domain.dto.CompetitorValuationDto;
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

    public List<CompetitorBalanceSheetDto> getCompetitorBalanceSheet(String companyName) {
        List<String> competitorCompanies = relationRepository.findCompetitorNamesByCompanyName(companyName);
        competitorCompanies.add(0, companyName);

        List<CompetitorBalanceSheetDto> competitorBalanceSheetDtos = new ArrayList<>();

        for(String company : competitorCompanies) {
            CompanyFinancialEntity financialEntity = companyFinancialRepository.findByCompanyName(company);
            if (financialEntity != null) {
                competitorBalanceSheetDtos.add(
                        new CompetitorBalanceSheetDto(
                                company,
                                financialEntity.getTotalEquity(),
                                financialEntity.getTotalLiabilities(),
                                financialEntity.getOperIncome()
                        )
                );
            }
        }

        return competitorBalanceSheetDtos;
    }

    public List<CompetitorValuationDto> getCompetitorValuation(String companyName){
        List<String> competitorCompanies = relationRepository.findCompetitorNamesByCompanyName(companyName);
        competitorCompanies.add(0, companyName);

        List<CompetitorValuationDto> competitorValuationDtos = new ArrayList<>();

        for(String company: competitorCompanies) {
            CompanyFinancialEntity financialEntity = companyFinancialRepository.findByCompanyName(company);
            if(financialEntity != null) {
                competitorValuationDtos.add(
                        new CompetitorValuationDto(
                                company,
                                financialEntity.getPER(),
                                financialEntity.getPBR()
                        )
                );
            }
        }

        return competitorValuationDtos;
    }
}
