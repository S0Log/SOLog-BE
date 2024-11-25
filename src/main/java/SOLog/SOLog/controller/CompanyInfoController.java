package SOLog.SOLog.controller;

import SOLog.SOLog.domain.dto.CompanyOverviewDto;
import SOLog.SOLog.service.CompanyInfoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/companyInfo")
public class CompanyInfoController {
    private final CompanyInfoService companyInfoService;

    @GetMapping("/companyOverview/{companyName}")
    @Operation(summary = "기업 개요", description = "기업정보 내 기업개요 정보 반환")
    public CompanyOverviewDto CompanyOverview(@PathVariable String companyName) {
        return companyInfoService.getCompanyOverview(companyName);
    }
}
