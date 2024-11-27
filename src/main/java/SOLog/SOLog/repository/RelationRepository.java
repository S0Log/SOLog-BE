package SOLog.SOLog.repository;

import SOLog.SOLog.domain.entity.RelationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RelationRepository extends JpaRepository<RelationEntity, Long> {
    @Query("SELECT r.competitor.companyName FROM RelationEntity r WHERE r.company.companyName = :companyName")
    List<String> findCompetitorNamesByCompanyName(@Param("companyName") String companyName);

}
