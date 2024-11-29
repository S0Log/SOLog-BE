package SOLog.SOLog.repository;

import SOLog.SOLog.domain.entity.RelationEntity;
import SOLog.SOLog.domain.entity.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ReportRepository extends JpaRepository<ReportEntity, Long> {
    @Query(value = "SELECT * FROM report_entity " +
            "WHERE company_name = :companyName " +
            "AND date BETWEEN :endDate AND :startDate " +
            "ORDER BY date DESC",
            nativeQuery = true)
    List<ReportEntity> findReportsByCompanyNameAndDateRange(
            @Param("companyName") String companyName,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate);
}
