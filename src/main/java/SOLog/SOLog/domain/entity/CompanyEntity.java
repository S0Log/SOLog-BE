package SOLog.SOLog.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyEntity {
    @Id
    private String companyName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date listedDate;

    @Column(name = "overview",length = 1000)
    private String overview;

    @ColumnDefault("'코스피'")
    private String marketType;

    private String capitalAmount;

    private Date overviewDate;


}
