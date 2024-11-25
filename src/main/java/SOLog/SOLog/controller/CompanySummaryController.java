package SOLog.SOLog.controller;

import SOLog.SOLog.domain.dto.CompanySummaryDto;
import SOLog.SOLog.service.CompanySummaryService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CompanySummaryController {
    private final CompanySummaryService companySummaryService;

    @GetMapping("/{companyName}/companySummary")
    @Operation(summary = "기업요약", description = "차트분석 & 과거비교 기업요약")
    public CompanySummaryDto companySummary(
            @PathVariable String companyName,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
            @RequestParam String durationType) {
        return companySummaryService.getCompanySummary(companyName, date, durationType);
    }
}
