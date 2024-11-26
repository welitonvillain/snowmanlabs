package com.snowmanlabs.challenge.movie.application.usecase;

import com.snowmanlabs.challenge.movie.domain.exception.MovieNotFoundException;
import com.snowmanlabs.challenge.movie.domain.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DeleteMovieUseCase {

    private final MovieRepository movieRepository;

    public void execute(final Long id) {
        var movie = movieRepository.findById(id);
        if (Objects.isNull(movie)) {
            throw new MovieNotFoundException(String.format("The movie with id %s was not found.", id));
        }

        movieRepository.delete(id);
    }

}
