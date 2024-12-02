package SOLog.SOLog.repository;

import SOLog.SOLog.domain.entity.IdentifierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdentifierRepository extends JpaRepository<IdentifierEntity, String> {
    IdentifierEntity findByCompany_CompanyName(String companyName);
}
