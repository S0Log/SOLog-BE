package SOLog.SOLog.service;

import SOLog.SOLog.domain.entity.GlossaryEntity;
import SOLog.SOLog.repository.GlossaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GlossaryService {
    private final GlossaryRepository glossaryRepository;
    public GlossaryEntity getDefinition(String term){
        return glossaryRepository.findByTerm(term);
    }

}
