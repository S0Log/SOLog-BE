package SOLog.SOLog.repository;

import SOLog.SOLog.domain.entity.CompanyEntity;
import SOLog.SOLog.domain.entity.CompanyFinancialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.Optional;

public interface CompanyFinancialRepository extends JpaRepository<CompanyFinancialEntity,String> {
    @Query(value = "SELECT * FROM company_financial_entity " +
            "WHERE company_name = :companyName " +
            "AND date < :date " +
            "ORDER BY date DESC LIMIT 1", nativeQuery = true)
    Optional<CompanyFinancialEntity> findTopByCompanyNameAndDateBeforeNative(
            @Param("companyName") String companyName,
            @Param("date") Date date);

    @Query(value = "SELECT * FROM company_financial_entity " +
            "WHERE company_name = :companyName " +
            "AND date <= :date " +
            "ORDER BY date DESC " +
            "LIMIT 1", nativeQuery = true)
    CompanyFinancialEntity findClosestByCompanyNameAndDate(@Param("companyName") String companyName, @Param("date") Date date);

    @Query(value = "SELECT * FROM company_financial_entity WHERE company_name = :companyName AND date like '3000%' LIMIT 1", nativeQuery = true)
    CompanyFinancialEntity findByCompanyName(@Param("companyName") String companyName);

}
