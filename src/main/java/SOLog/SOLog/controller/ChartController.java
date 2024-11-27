package SOLog.SOLog.controller;

import SOLog.SOLog.domain.dto.ChartDataResponseDto;
import SOLog.SOLog.domain.dto.DurationChartRequestDto;
import SOLog.SOLog.domain.dto.TrendMatchChartRequestDto;
import SOLog.SOLog.domain.entity.StockDataEntity;
import SOLog.SOLog.service.ChartServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chart")
public class ChartController {
    private final ChartServiceImpl chartService;

    @GetMapping("/select-duration-type")//차트분석탭 차트
    @Operation(summary = "차트 분석", description = "차트분석 내 차트 주가데이터 반환")
    public List<ChartDataResponseDto> selectDurationChart(DurationChartRequestDto requestDto){

        return chartService.getCompanyStockData(requestDto.getCompanyName(),requestDto.getDurationType());

    }
    @GetMapping("/company-detail")//기업상세탭 차트
    @Operation(summary = "기업 상세", description = "기업상세 내 차트 주가데이터 반환")
    public List<ChartDataResponseDto> companyDetailChart(DurationChartRequestDto requestDto){

        return chartService.getCompanyStockDataSetStart(requestDto.getCompanyName(),requestDto.getDurationType());

    }
    @GetMapping("/trend-match")
    @Operation(summary = "과거비교", description = "과거비교 내 유사 차트 주가데이터 반환")
    public List<ChartDataResponseDto>[] trendMatchChart(TrendMatchChartRequestDto requestDto){

        return chartService.getTrendMatchStockData(requestDto.getCompanyName(),requestDto.getBaseDate(),requestDto.getStartDate(),requestDto.getPeriod());

    }

}
