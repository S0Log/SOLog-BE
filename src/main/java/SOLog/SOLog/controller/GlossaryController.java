package SOLog.SOLog.controller;

import SOLog.SOLog.domain.entity.GlossaryEntity;
import SOLog.SOLog.service.GlossaryService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/glossary")
public class GlossaryController {
    private final GlossaryService glossaryService;
    @GetMapping("/{term}")
    @Operation(summary = "용어정의", description = "기업상세 내 용어 클릭시 정의 반환")
    public GlossaryEntity termDefinition(@PathVariable String term) {
        return glossaryService.getDefinition(term);
    }

}
