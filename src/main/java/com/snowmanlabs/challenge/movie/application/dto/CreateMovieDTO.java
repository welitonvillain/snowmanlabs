package com.snowmanlabs.challenge.movie.application.dto;

import com.snowmanlabs.challenge.shared.domain.type.Format;
import com.snowmanlabs.challenge.shared.domain.type.Genre;
import com.snowmanlabs.challenge.movie.domain.entity.Movie;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateMovieDTO(
        @NotEmpty String title,
        @NotNull int year,
        @NotNull Format format,
        @NotNull Genre genre,
        @NotNull int duration
) {

    public Movie toDomain() {
        return new Movie(null, title, year, format, genre, duration, true);
    }
}
