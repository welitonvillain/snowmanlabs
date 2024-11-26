package com.snowmanlabs.challenge.series.infrastructure.in.web;

import com.snowmanlabs.challenge.series.application.dto.SeriesDTO;
import com.snowmanlabs.challenge.series.application.dto.UpdateSeriesDTO;
import com.snowmanlabs.challenge.series.application.usecase.UpdateSeriesUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/series")
@RequiredArgsConstructor
@Tag(name = "Series", description = "Operations related to the series model.")
public class UpdateSeriesController {

    private final UpdateSeriesUseCase updateSeriesUseCase;

    @PutMapping
    @Operation(summary = "Update a series")
    public SeriesDTO execute(@Valid @RequestBody UpdateSeriesDTO dto) {
        return updateSeriesUseCase.execute(dto);
    }


}
