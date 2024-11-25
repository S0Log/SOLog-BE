package SOLog.SOLog.repository;

import SOLog.SOLog.domain.entity.StockDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface StockDataRepository extends JpaRepository<StockDataEntity,String> {
    List<StockDataEntity> findByCompany_CompanyNameAndDurationType(String companyName, String durationType);

    @Query("SELECT s FROM StockDataEntity s WHERE s.company.companyName = :companyName AND s.date = :date AND s.durationType= :type")
    StockDataEntity findByCompanyNameAndDateByDay(@Param("companyName") String companyName, @Param("date") Date date, @Param("type") String durationType);
}
