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
//    @Query("SELECT s FROM StockDataEntity s " +
//            "WHERE s.company.companyName = :companyName " +
//            "AND s.date BETWEEN :startDate AND :endDate " +
//            "AND s.durationType = 'day' " +
//            "ORDER BY s.date ASC")
//    List<StockDataEntity> findDurationTypeAndStockDataBetweenDates(
//            @Param("companyName") String companyName,
//            @Param("startDate") Date startDate,
//            @Param("endDate") Date endDate);

    @Query(value = "SELECT * FROM stock_data_entity " +
            "WHERE company_name = :companyName " +
            "AND date <= :date " +
            "AND duration_type = 'day' " +
            "ORDER BY date DESC " +
            "LIMIT 25",
            nativeQuery = true)
    List<StockDataEntity> findByCompany_CompanyNameAndDurationTypeAndDateBefore(@Param("companyName") String companyName,
                                                                                @Param("date") Date date);
    @Query(value = "SELECT * FROM stock_data_entity " +
            "WHERE company_name = :companyName " +
            "AND date > :date " +
            "AND duration_type = 'day' " +
            "ORDER BY date ASC " +
            "LIMIT 5",
            nativeQuery = true)
    List<StockDataEntity> findByCompany_CompanyNameAndDurationTypeAndDateAfter(@Param("companyName") String companyName,
                                                                               @Param("date") Date date);

    @Query("SELECT s FROM StockDataEntity s WHERE s.company.companyName = :companyName AND s.date = :date AND s.durationType= :type")
    StockDataEntity findByCompanyNameAndDateByDay(@Param("companyName") String companyName, @Param("date") Date date, @Param("type") String durationType);

    @Query(value = "SELECT * FROM stock_data_entity s WHERE s.company_name = :companyName AND s.duration_type = 'day' ORDER BY s.date DESC LIMIT 2", nativeQuery = true)
    List<StockDataEntity> findTop2ByCompanyNameAndDurationType(String companyName);


}
