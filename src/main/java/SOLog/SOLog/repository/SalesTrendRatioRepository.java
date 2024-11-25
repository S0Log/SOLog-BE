package SOLog.SOLog.repository;

import SOLog.SOLog.domain.entity.SalesTrendRatioEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SalesTrendRatioRepository extends JpaRepository<SalesTrendRatioEntity, Integer> {

    @Query("SELECT s FROM SalesTrendRatioEntity s WHERE s.company.companyName = :companyName")
    Page<SalesTrendRatioEntity> findTop5ByCompanyName(@Param("companyName") String companyName, Pageable pageable);
}
