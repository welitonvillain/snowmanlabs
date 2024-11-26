package com.snowmanlabs.challenge.series.infrastructure.out.persistence.entity;

import com.snowmanlabs.challenge.series.domain.entity.Series;
import com.snowmanlabs.challenge.shared.domain.type.Format;
import com.snowmanlabs.challenge.shared.domain.type.Genre;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@Entity
@Table(name = "series")
@NoArgsConstructor
@AllArgsConstructor
public class SeriesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "release_year")
    private int year;

    @Column(name = "format")
    @Enumerated(EnumType.STRING)
    private Format format;

    @Column(name = "genre")
    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Column(name = "season")
    private int season;

    @Column(name = "episodes")
    private int episodes;

    @Column(name = "available")
    private boolean available;


    public static SeriesEntity toEntity(Series series) {
        return SeriesEntity.builder()
                .id(series.id())
                .title(series.title())
                .year(series.year())
                .format(series.format())
                .genre(series.genre())
                .season(series.season())
                .episodes(series.episodes())
                .available(series.available())
                .build();
    }

    public Series toDomain() {
        return new Series(this.id, this.title, this.year, this.format, this.genre, this.season, this.episodes, this.available);
    }

}
