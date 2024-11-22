package SOLog.SOLog.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyFinancialEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "companyName")
    private CompanyEntity company;

    private Date date;
    private String EPS;
    private String BPS;
    private String ROA;
    private String dividendPerShare;//배당비율
    private String revenue;//매출액
    private String quarterlyHigh;
    private String quarterlyLow;
    private String netIncome;//당기순이익
    private String operIncome;//영업이익
    private String PER;
    private String PBR;
    private String ROE;
    private Long totalAsset; //자산총계
    private Long totalEquity;//자본총계
    private Long totalLiabilities;//부채총계
    private String operatingMargin;//영업이익 경쟁사용
    private String revenueGrowthRate;//매출액 증가율
    private String marketCapital;//시가총액


}
