package SOLog.SOLog.controller;

import SOLog.SOLog.domain.dto.CompanyOverviewDto;
import SOLog.SOLog.domain.dto.MarketShareDto;
import SOLog.SOLog.service.CompanyInfoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/companyInfo")
public class CompanyInfoController {
    private final CompanyInfoService companyInfoService;

    @GetMapping("/companyOverview/{companyName}")
    @Operation(summary = "기업 개요", description = "기업정보 내 기업개요 정보 반환")
    public CompanyOverviewDto companyOverview(@PathVariable String companyName) {
        return companyInfoService.getCompanyOverview(companyName);
    }

    @GetMapping("/{companyName}/marketShare")
    @Operation(summary = "시장점유율", description = "기업정보 내 시장점유율 정보 반환")
    public List<MarketShareDto> marketShare(@PathVariable String companyName) {
        return companyInfoService.getMarketShare(companyName);
    }
}
