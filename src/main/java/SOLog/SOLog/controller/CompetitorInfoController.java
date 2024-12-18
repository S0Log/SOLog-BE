package SOLog.SOLog.controller;

import SOLog.SOLog.domain.dto.CompetitorBalanceSheetDto;
import SOLog.SOLog.domain.dto.CompetitorPriceDto;
import SOLog.SOLog.domain.dto.CompetitorProfitabilityDto;
import SOLog.SOLog.domain.dto.CompetitorValuationDto;
import SOLog.SOLog.service.CompetitorInfoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/competitorInfo")
public class CompetitorInfoController {
    private final CompetitorInfoService competitorInfoService;

    @GetMapping("/{companyName}/price")
    @Operation(summary = "경쟁사 비교 내 price", description = "경쟁사들의 price 정보 반환")
    public List<CompetitorPriceDto> competitorPrice(@PathVariable String companyName) {

        return competitorInfoService.getCompetitorPrice(companyName);
    }

    @GetMapping("/{companyName}/balanceSheet")
    @Operation(summary = "경쟁사 비교 내 balanceSheet", description = "경쟁사들의 balanceSheet 정보 반환")
    public List<CompetitorBalanceSheetDto> competitorBalanceSheet(@PathVariable String companyName){
        return competitorInfoService.getCompetitorBalanceSheet(companyName);
    }

    @GetMapping("/{companyName}/valuation")
    @Operation(summary = "경쟁사 배교 내 valuation", description = "경재사들의 valuation 정보 반환")
    public List<CompetitorValuationDto> competitorValuation(@PathVariable String companyName) {
        return competitorInfoService.getCompetitorValuation(companyName);
    }

    @GetMapping("/{companyName}/profitability")
    @Operation(summary = "경쟁사 배교 내 profitability", description = "경쟁사들의 profitability 정보 반환")
    public List<CompetitorProfitabilityDto> competitorProfitability(@PathVariable String companyName) {
        return competitorInfoService.getCompetitorProfitability(companyName);
    }
}
