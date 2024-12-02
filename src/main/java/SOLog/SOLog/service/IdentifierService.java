package SOLog.SOLog.service;

import SOLog.SOLog.repository.IdentifierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IdentifierService {
    private final IdentifierRepository identifierRepository;
    public String getCompanyNum(String companyName) {
        return identifierRepository.findByCompany_CompanyName(companyName).getCompanyNum();
    }
}
