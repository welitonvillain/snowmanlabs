package com.snowmanlabs.challenge.series.infrastructure.in.web;

import com.snowmanlabs.challenge.series.application.dto.SeriesDTO;
import com.snowmanlabs.challenge.series.application.usecase.FindSeriesByIdUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/series")
@RequiredArgsConstructor
@Tag(name = "Series", description = "Operations related to the series model.")
public class FindSeriesByIdController {

    private final FindSeriesByIdUseCase findSeriesByIdUseCase;

    @GetMapping("/{id}")
    @Operation(summary = "Find series by ID")
    public SeriesDTO execute(@PathVariable("id") Long id) {
        return findSeriesByIdUseCase.execute(id);
    }


}
