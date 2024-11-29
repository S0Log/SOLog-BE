package SOLog.SOLog.controller;

import SOLog.SOLog.domain.dto.ReportResponseDto;
import SOLog.SOLog.domain.entity.GlossaryEntity;
import SOLog.SOLog.domain.entity.ReportEntity;
import SOLog.SOLog.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stock-insight")
public class StockInsightController {
    private final ReportService reportService;

    @GetMapping("/reports/{companyName}")
    @Operation(summary = "레포트", description = "종목별 레포트 데이터 반환")
    public List<ReportResponseDto> reports(@PathVariable String companyName,
                                           @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date
                                         ) {
        return reportService.getReportRepository(companyName,date);
    }

}
