package com.snowmanlabs.challenge.series.application.dto;

import com.snowmanlabs.challenge.series.domain.entity.Series;
import com.snowmanlabs.challenge.shared.domain.type.Format;
import com.snowmanlabs.challenge.shared.domain.type.Genre;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UpdateSeriesDTO(
        @NotNull Long id,
        @NotEmpty String title,
        @NotNull int year,
        @NotNull Format format,
        @NotNull Genre genre,
        @NotNull int season,
        @NotNull int episodes,
        @NotNull boolean available
) {

    public static UpdateSeriesDTO fromDomain(final Series series) {
        return new UpdateSeriesDTO(series.id(), series.title(), series.year(), series.format(),
                series.genre(), series.season(), series.episodes(), series.available());
    }

    public Series toDomain() {
        return new Series(id, title, year, format, genre, season, episodes, available);
    }
}
