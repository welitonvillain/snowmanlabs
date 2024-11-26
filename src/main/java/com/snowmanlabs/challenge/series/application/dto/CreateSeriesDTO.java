package com.snowmanlabs.challenge.series.application.dto;

import com.snowmanlabs.challenge.series.domain.entity.Series;
import com.snowmanlabs.challenge.shared.domain.type.Format;
import com.snowmanlabs.challenge.shared.domain.type.Genre;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateSeriesDTO(
        @NotEmpty String title,
        @NotNull int year,
        @NotNull Format format,
        @NotNull Genre genre,
        @NotNull int episodes,
        @NotNull int season
) {

    public Series toDomain() {
        return new Series(null, title, year, format, genre, season, episodes, true);
    }
}
