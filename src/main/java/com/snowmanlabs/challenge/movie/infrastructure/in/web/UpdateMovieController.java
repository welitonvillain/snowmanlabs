package com.snowmanlabs.challenge.movie.infrastructure.in.web;

import com.snowmanlabs.challenge.movie.application.dto.MovieDTO;
import com.snowmanlabs.challenge.movie.application.dto.UpdateMovieDTO;
import com.snowmanlabs.challenge.movie.application.usecase.UpdateMovieUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/movies")
@RequiredArgsConstructor
@Tag(name = "Movies", description = "Operations related to the movie model.")
public class UpdateMovieController {

    private final UpdateMovieUseCase updateMovieUseCase;

    @PutMapping
    @Operation(summary = "Update a movie")
    public MovieDTO execute(@Valid @RequestBody UpdateMovieDTO dto) {
        return updateMovieUseCase.execute(dto);
    }


}
