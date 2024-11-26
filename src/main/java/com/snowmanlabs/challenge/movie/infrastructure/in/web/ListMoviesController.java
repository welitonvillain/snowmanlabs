package com.snowmanlabs.challenge.movie.infrastructure.in.web;

import com.snowmanlabs.challenge.movie.application.dto.MovieDTO;
import com.snowmanlabs.challenge.movie.application.usecase.ListMoviesUseCase;
import com.snowmanlabs.challenge.shared.domain.pagination.PageResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/movies")
@RequiredArgsConstructor
@Tag(name = "Movies", description = "Operations related to the movie model.")
public class ListMoviesController {

    private final ListMoviesUseCase listMoviesUseCase;

    @GetMapping
    @Operation(summary = "List all movies with pagination and sorting.")
    public PageResult<MovieDTO> execute(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "title") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        return listMoviesUseCase.execute(page, size, sortBy, direction);
    }


}
