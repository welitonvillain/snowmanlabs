package com.snowmanlabs.challenge.movie.application.dto;

import com.snowmanlabs.challenge.shared.domain.type.Format;
import com.snowmanlabs.challenge.shared.domain.type.Genre;
import com.snowmanlabs.challenge.movie.domain.entity.Movie;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UpdateMovieDTO(
        @NotNull Long id,
        @NotEmpty String title,
        @NotNull int year,
        @NotNull Format format,
        @NotNull Genre genre,
        @NotNull int duration,
        @NotNull boolean available
) {

    public static UpdateMovieDTO fromDomain(final Movie movie) {
        return new UpdateMovieDTO(movie.id(), movie.title(), movie.year(), movie.format(),
                movie.genre(), movie.duration(), movie.available());
    }

    public Movie toDomain() {
        return new Movie(id, title, year, format, genre, duration, available);
    }
}
