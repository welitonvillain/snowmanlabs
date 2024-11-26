package com.snowmanlabs.challenge.series.infrastructure.in.web;

import com.snowmanlabs.challenge.series.application.dto.CreateSeriesDTO;
import com.snowmanlabs.challenge.series.application.dto.SeriesDTO;
import com.snowmanlabs.challenge.series.application.usecase.CreateSeriesUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/series")
@RequiredArgsConstructor
@Tag(name = "Series", description = "Operations related to the series model.")
public class CreateSeriesController {

    private final CreateSeriesUseCase createSeriesUseCase;

    @PostMapping
    @Operation(summary = "Create a Series")
    public SeriesDTO execute(@Valid @RequestBody CreateSeriesDTO dto) {
        return createSeriesUseCase.execute(dto);
    }


}
