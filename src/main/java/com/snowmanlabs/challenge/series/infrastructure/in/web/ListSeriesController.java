package com.snowmanlabs.challenge.series.infrastructure.in.web;

import com.snowmanlabs.challenge.series.application.dto.SeriesDTO;
import com.snowmanlabs.challenge.series.application.usecase.ListSeriesUseCase;
import com.snowmanlabs.challenge.shared.domain.pagination.PageResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/series")
@RequiredArgsConstructor
@Tag(name = "Series", description = "Operations related to the series model.")
public class ListSeriesController {

    private final ListSeriesUseCase listSeriesUseCase;

    @GetMapping
    @Operation(summary = "List all series with pagination and sorting.")
    public PageResult<SeriesDTO> execute(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "title") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        return listSeriesUseCase.execute(page, size, sortBy, direction);
    }


}
