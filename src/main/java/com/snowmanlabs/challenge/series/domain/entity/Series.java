package com.snowmanlabs.challenge.series.domain.entity;

import com.snowmanlabs.challenge.shared.domain.entity.Media;
import com.snowmanlabs.challenge.shared.domain.type.Format;
import com.snowmanlabs.challenge.shared.domain.type.Genre;

public record Series(Long id, String title, int year, Format format,
                     Genre genre, int season, int episodes, boolean available) implements Media {

}
