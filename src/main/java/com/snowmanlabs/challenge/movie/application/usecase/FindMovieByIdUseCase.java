package com.snowmanlabs.challenge.movie.application.usecase;

import com.snowmanlabs.challenge.movie.application.dto.MovieDTO;
import com.snowmanlabs.challenge.movie.domain.entity.Movie;
import com.snowmanlabs.challenge.movie.domain.exception.MovieNotFoundException;
import com.snowmanlabs.challenge.movie.domain.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FindMovieByIdUseCase {

    private final MovieRepository movieRepository;

    public MovieDTO execute(final Long id) {
        Movie domainMovie = movieRepository.findById(id);

        if (Objects.isNull(domainMovie))
            throw new MovieNotFoundException(String.format("The movie with id %s was not found.", id));

        return MovieDTO.fromDomain(domainMovie);
    }

}
