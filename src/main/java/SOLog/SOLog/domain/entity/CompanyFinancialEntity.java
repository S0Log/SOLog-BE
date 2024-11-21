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
    private String revenue;
    private String quarterlyHigh;
    private String quarterlyLow;
    private String netIncome;
    private String operIncome;
    private String PER;
    private String PBR;
    private String ROE;
    private Long totalAsset;
    private Long totalEquity;
    private Long totalLiabilities;
    private String operatingMargin;
    private String revenueGrowthRate;


}
