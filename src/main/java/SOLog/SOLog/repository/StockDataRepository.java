package SOLog.SOLog.repository;

import SOLog.SOLog.domain.entity.StockDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockDataRepository extends JpaRepository<StockDataEntity,String> {
    List<StockDataEntity> findByCompany_CompanyNameAndDurationType(String companyName, String durationType);
}
