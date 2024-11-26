package com.snowmanlabs.challenge.series.application.dto;

import com.snowmanlabs.challenge.series.domain.entity.Series;
import com.snowmanlabs.challenge.shared.domain.type.Format;
import com.snowmanlabs.challenge.shared.domain.type.Genre;

public record SeriesDTO(
        Long id, String title, int year, Format format,
        Genre genre, int season, int episodes, boolean available
) {

    public static SeriesDTO fromDomain(final Series series) {
        return new SeriesDTO(series.id(), series.title(), series.year(), series.format(),
                series.genre(), series.season(), series.episodes(), series.available());
    }
}
