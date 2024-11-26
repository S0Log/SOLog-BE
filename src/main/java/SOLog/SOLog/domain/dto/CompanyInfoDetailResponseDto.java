package SOLog.SOLog.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
public class CompanyInfoDetailResponseDto {

    private String companyName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private String EPS;
    private String BPS;
    private String ROA;
    private String revenue;//매출액
    private String quarterlyHigh;
    private String quarterlyLow;
    private String netIncome;//당기순이익
    private String operIncome;//영업이익
    private String PER;
    private String PBR;
    private String ROE;
    private Double totalAsset; //자산총계
    private Double totalEquity;//자본총계
    private Double totalLiabilities;//부채총계
    private String operatingMargin;//영업이익 경쟁사용
    private String revenueGrowthRate;//매출액 증가율
    private String marketType;
    private String capitalAmount;
    private Date listedDate;

}
