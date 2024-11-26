package SOLog.SOLog.service;

import SOLog.SOLog.domain.dto.CompanyInfoDetailResponseDto;
import SOLog.SOLog.domain.entity.CompanyEntity;
import SOLog.SOLog.domain.entity.CompanyFinancialEntity;
import SOLog.SOLog.repository.CompanyFinancialRepository;
import SOLog.SOLog.repository.CompanyInfoRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@RequiredArgsConstructor

public class CompanyInfoDetailService {
    private final CompanyInfoRepository companyInfoRepository;
    private final CompanyFinancialRepository companyFinancialRepository;

    public CompanyInfoDetailResponseDto getCompanyInfoDetail(String companyName, Date date){

        CompanyEntity company = companyInfoRepository.findById(companyName)
                .orElseThrow(() -> new IllegalArgumentException("Company not found with name: " + companyName));
        CompanyFinancialEntity companyFinancial = companyFinancialRepository.findTopByCompanyNameAndDateBeforeNative(companyName,date)
                .orElseThrow(()-> new IllegalArgumentException("데이터가없습니다."));
        System.out.println(companyFinancial);


        return new CompanyInfoDetailResponseDto(
                company.getCompanyName(),
                companyFinancial.getDate(),
                companyFinancial.getEPS(),
                companyFinancial.getBPS(),
                companyFinancial.getROA(),
                companyFinancial.getRevenue(),
                companyFinancial.getQuarterlyHigh(),
                companyFinancial.getQuarterlyLow(),
                companyFinancial.getNetIncome(),
                companyFinancial.getOperIncome(),
                companyFinancial.getPER(),
                companyFinancial.getPBR(),
                companyFinancial.getROE(),
                companyFinancial.getTotalAsset(),
                companyFinancial.getTotalEquity(),
                companyFinancial.getTotalLiabilities(),
                companyFinancial.getOperatingMargin(),
                companyFinancial.getRevenueGrowthRate(),
                company.getMarketType(),
                company.getCapitalAmount(),
                company.getListedDate()
        );

    }

}
