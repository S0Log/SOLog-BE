package SOLog.SOLog.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PriceDiffEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date;
    private Double diff;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "companyName")
    private CompanyEntity company;
}
