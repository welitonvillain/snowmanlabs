package com.snowmanlabs.challenge.movie.application.dto;

import com.snowmanlabs.challenge.shared.domain.type.Format;
import com.snowmanlabs.challenge.shared.domain.type.Genre;
import com.snowmanlabs.challenge.movie.domain.entity.Movie;

public record MovieDTO(
        Long id, String title, int year, Format format, Genre genre, int duration, boolean available
) {

    public static MovieDTO fromDomain(final Movie movie) {
        return new MovieDTO(movie.id(), movie.title(), movie.year(), movie.format(),
                movie.genre(), movie.duration(), movie.available());
    }
}
