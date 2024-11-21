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
public class StockDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "companyName")
    private CompanyEntity company;

    private String durationType;

    private Date date;
    private Long highPrice;
    private Long lowPrice;
    private Long openPrice;
    private Long closePrice;
    private Long volume;


}
