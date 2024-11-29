package SOLog.SOLog.domain.dto;

import SOLog.SOLog.domain.entity.CompanyEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
public class ReportResponseDto {


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    private String writer;

    private String companyName;

    private String title;

    private String url;
}
