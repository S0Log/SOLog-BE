package SOLog.SOLog.controller;

import SOLog.SOLog.domain.dto.ChartDataResponseDto;
import SOLog.SOLog.domain.dto.DurationChartRequestDto;
import SOLog.SOLog.service.ChartServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chart")
public class ChartController {
    private final ChartServiceImpl chartService;

    @PostMapping("/select-duration-type")//차트분석탭 차트
    public List<ChartDataResponseDto> selectDurationChart(@RequestBody DurationChartRequestDto requestDto){

        return chartService.getCompanyStockData(requestDto.getCompanyName(),requestDto.getDurationType());

    }
    @PostMapping("/company-detail")//차트분석탭 차트
    public List<ChartDataResponseDto> companyDetailChart(@RequestBody DurationChartRequestDto requestDto){

        return chartService.getCompanyStockDataSetStart(requestDto.getCompanyName(),requestDto.getDurationType());

    }

}
