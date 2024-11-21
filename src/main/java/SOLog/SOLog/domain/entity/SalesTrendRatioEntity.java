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
public class SalesTrendRatioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;
    private Date date;
    private String salesPercent;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "companyName")
    private CompanyEntity company;
}
