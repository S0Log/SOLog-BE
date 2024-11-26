package SOLog.SOLog.controller;

import SOLog.SOLog.domain.dto.*;
import SOLog.SOLog.service.CompanyInfoDetailService;
import SOLog.SOLog.service.CompanyInfoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/companyInfo")
public class CompanyInfoController {
    private final CompanyInfoService companyInfoService;
    private final CompanyInfoDetailService companyInfoDetailService;

    @GetMapping("/{companyName}/companyOverview")
    @Operation(summary = "기업 개요", description = "기업정보 내 기업개요 정보 반환")
    public CompanyOverviewDto companyOverview(@PathVariable String companyName) {
        return companyInfoService.getCompanyOverview(companyName);
    }

    @GetMapping("/{companyName}/marketShare")
    @Operation(summary = "시장점유율", description = "기업정보 내 시장점유율 정보 반환")
    public List<MarketShareDto> marketShare(@PathVariable String companyName) {
        return companyInfoService.getMarketShare(companyName);
    }

    @GetMapping("/{companyName}/salesTrendRatio")
    @Operation(summary = "매출비중추이", description = "기업정보 내 매출비중추이 정보 반환")
    public List<SalesTrendRatioDto> salesTrendRatio(@PathVariable String companyName) {
        return companyInfoService.getSalesTrendRatio(companyName);
    }

    @GetMapping("/detail")
    @Operation(summary = "기업상세정보", description = "기업상세 내 기업상세정보")
    public ResponseEntity<Object> companyInfoDetail(CompanyInfoDetailRequestDto requestDto) {
        try {
            CompanyInfoDetailResponseDto response = companyInfoDetailService.getCompanyInfoDetail(requestDto.getCompanyName(), requestDto.getDate());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", e.getMessage()));
        }
    }



}
