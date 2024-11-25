package SOLog.SOLog.repository;

import SOLog.SOLog.domain.entity.MarketShareEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MarketShareRepository extends JpaRepository<MarketShareEntity, Integer> {

    @Query("SELECT m FROM MarketShareEntity m WHERE m.company.companyName = :companyName")
    List<MarketShareEntity> findByCompanyName(@Param("companyName") String companyName);
}
