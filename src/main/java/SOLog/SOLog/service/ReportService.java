package SOLog.SOLog.service;

import SOLog.SOLog.domain.dto.ChartDataResponseDto;
import SOLog.SOLog.domain.dto.ReportResponseDto;
import SOLog.SOLog.domain.entity.ReportEntity;
import SOLog.SOLog.domain.entity.StockDataEntity;
import SOLog.SOLog.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final ReportRepository reportRepository;

    public List<ReportResponseDto> getReportRepository(String companyName, Date startDate) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.MONTH, -2);
        Date endDate = calendar.getTime();

        List<ReportEntity> reportEntities = reportRepository.findReportsByCompanyNameAndDateRange(companyName, startDate, endDate);


        return reportEntities.stream()
                .sorted(Comparator.comparing(ReportEntity::getDate).reversed())
                .map(ReportEntity -> new ReportResponseDto(
                        ReportEntity.getDate(),
                        ReportEntity.getWriter(),
                        ReportEntity.getCompanyName().getCompanyName(),
                        ReportEntity.getTitle(),
                        ReportEntity.getUrl()
                ))
                .collect(Collectors.toList());
    }
}
