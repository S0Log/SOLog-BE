package SOLog.SOLog.repository;

import SOLog.SOLog.domain.entity.PriceDiffEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PriceDiffRepository extends JpaRepository<PriceDiffEntity,Long> {
    @Query("SELECT p FROM PriceDiffEntity p WHERE p.company.companyName = :companyName AND p.date < :date")
    List<PriceDiffEntity> findPriceDiffByConditions(@Param("companyName") String companyName, @Param("date") Date date);

}
