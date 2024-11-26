package com.snowmanlabs.challenge.rental.infrastructure.out.persistence.repository;

import com.snowmanlabs.challenge.movie.domain.repository.MovieRepository;
import com.snowmanlabs.challenge.shared.domain.entity.Media;
import com.snowmanlabs.challenge.shared.domain.repository.MediaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MovieRentalRepository implements MediaRepository {

    private final MovieRepository movieRepository;

    @Override
    public Optional<Media> findById(final Long id) {
        var movie = movieRepository.findById(id);
        return Objects.nonNull(movie) ? Optional.of(movie) : Optional.empty();
    }

    @Override
    public void setMediaAvailable(Long id) {
        movieRepository.setMovieAvailable(id);
    }
}
