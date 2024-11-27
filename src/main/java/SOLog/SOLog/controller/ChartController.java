package SOLog.SOLog.controller;

import SOLog.SOLog.domain.dto.ChartDataResponseDto;
import SOLog.SOLog.domain.dto.DurationChartRequestDto;
import SOLog.SOLog.domain.dto.TrendMatchChartRequestDto;
import SOLog.SOLog.domain.entity.StockDataEntity;
import SOLog.SOLog.service.ChartServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chart")
public class ChartController {
    private final ChartServiceImpl chartService;

    @GetMapping("/select-duration-type")//차트분석탭 차트
    public List<ChartDataResponseDto> selectDurationChart(DurationChartRequestDto requestDto){

        return chartService.getCompanyStockData(requestDto.getCompanyName(),requestDto.getDurationType());

    }
    @GetMapping("/company-detail")//차트분석탭 차트
    public List<ChartDataResponseDto> companyDetailChart(DurationChartRequestDto requestDto){

        return chartService.getCompanyStockDataSetStart(requestDto.getCompanyName(),requestDto.getDurationType());

    }
    @GetMapping("/trend-match")
    public List<ChartDataResponseDto>[] trendMatchChart(TrendMatchChartRequestDto requestDto){

        return chartService.getTrendMatchStockData(requestDto.getCompanyName(),requestDto.getBaseDate(),requestDto.getStartDate(),requestDto.getPeriod());

    }

}
