package com.snowmanlabs.challenge.movie.domain.entity;

import com.snowmanlabs.challenge.shared.domain.entity.Media;
import com.snowmanlabs.challenge.shared.domain.type.Format;
import com.snowmanlabs.challenge.shared.domain.type.Genre;

public record Movie (Long id, String title, int year,
                     Format format, Genre genre, int duration, boolean available) implements Media {

}
