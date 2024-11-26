package com.snowmanlabs.challenge.movie.infrastructure.out.persistence.entity;

import com.snowmanlabs.challenge.shared.domain.type.Format;
import com.snowmanlabs.challenge.shared.domain.type.Genre;
import com.snowmanlabs.challenge.movie.domain.entity.Movie;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@Entity
@Table(name = "movies")
@NoArgsConstructor
@AllArgsConstructor
public class MovieEntity {

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

    @Column(name = "duration")
    private int duration;

    @Column(name = "available")
    private boolean available;

    public static MovieEntity toEntity(final Movie movie) {
        return MovieEntity.builder()
                .id(movie.id())
                .title(movie.title())
                .year(movie.year())
                .format(movie.format())
                .genre(movie.genre())
                .duration(movie.duration())
                .available(movie.available())
                .build();
    }

    public Movie toDomain() {
        return new Movie(this.id, this.title, this.year, this.format, this.genre, this.duration, this.available);
    }

}
