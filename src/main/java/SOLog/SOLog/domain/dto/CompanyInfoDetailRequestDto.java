package SOLog.SOLog.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
public class CompanyInfoDetailRequestDto {
    private String companyName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
}
