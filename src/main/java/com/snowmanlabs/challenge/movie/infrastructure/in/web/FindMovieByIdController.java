package com.snowmanlabs.challenge.movie.infrastructure.in.web;

import com.snowmanlabs.challenge.movie.application.dto.MovieDTO;
import com.snowmanlabs.challenge.movie.application.usecase.FindMovieByIdUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/movies")
@RequiredArgsConstructor
@Tag(name = "Movies", description = "Operations related to the movie model.")
public class FindMovieByIdController {

    private final FindMovieByIdUseCase findMovieByIdUseCase;

    @GetMapping("/{id}")
    @Operation(summary = "Find movie by ID")
    public MovieDTO execute(@PathVariable("id") Long id) {
        return findMovieByIdUseCase.execute(id);
    }


}
