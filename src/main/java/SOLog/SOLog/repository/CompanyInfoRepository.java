package SOLog.SOLog.repository;

import SOLog.SOLog.domain.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyInfoRepository extends JpaRepository<CompanyEntity, String> {
}
