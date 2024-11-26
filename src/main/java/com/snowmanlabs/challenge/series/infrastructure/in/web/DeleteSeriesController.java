package com.snowmanlabs.challenge.series.infrastructure.in.web;

import com.snowmanlabs.challenge.series.application.usecase.DeleteSeriesUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/series")
@RequiredArgsConstructor
@Tag(name = "Series", description = "Operations related to the series model.")
public class DeleteSeriesController {

    private final DeleteSeriesUseCase deleteSeriesUseCase;

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Series")
    public void execute(@PathVariable("id") Long id) {
        deleteSeriesUseCase.execute(id);
    }


}
