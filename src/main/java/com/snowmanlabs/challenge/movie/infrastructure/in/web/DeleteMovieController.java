package com.snowmanlabs.challenge.movie.infrastructure.in.web;

import com.snowmanlabs.challenge.movie.application.usecase.DeleteMovieUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/movies")
@RequiredArgsConstructor
@Tag(name = "Movies", description = "Operations related to the movie model.")
public class DeleteMovieController {

    private final DeleteMovieUseCase deleteMovieUseCase;

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Movie")
    public void execute(@PathVariable("id") Long id) {
        deleteMovieUseCase.execute(id);
    }


}
