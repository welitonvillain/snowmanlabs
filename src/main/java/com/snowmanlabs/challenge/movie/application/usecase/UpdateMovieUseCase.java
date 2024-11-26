package com.snowmanlabs.challenge.movie.application.usecase;

import com.snowmanlabs.challenge.movie.application.dto.MovieDTO;
import com.snowmanlabs.challenge.movie.application.dto.UpdateMovieDTO;
import com.snowmanlabs.challenge.movie.domain.exception.MovieNotFoundException;
import com.snowmanlabs.challenge.movie.domain.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UpdateMovieUseCase {

    private final MovieRepository movieRepository;

    public MovieDTO execute(final UpdateMovieDTO dto) {
        var domainMovie = movieRepository.findById(dto.id());

        if (Objects.isNull(domainMovie))
            throw new MovieNotFoundException(String.format("The movie with id %s was not found.", dto.id()));

        return MovieDTO.fromDomain(movieRepository.update(dto.id(), dto.toDomain()));
    }

}
