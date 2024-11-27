package SOLog.SOLog.repository;

import SOLog.SOLog.domain.entity.GlossaryEntity;
import SOLog.SOLog.domain.entity.MarketShareEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GlossaryRepository extends JpaRepository<GlossaryEntity,Long> {
    @Query("SELECT m FROM GlossaryEntity m WHERE m.term = :term")
    GlossaryEntity findByTerm(@Param("term") String term);
}
