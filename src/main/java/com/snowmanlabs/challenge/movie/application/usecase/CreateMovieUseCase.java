package com.snowmanlabs.challenge.movie.application.usecase;

import com.snowmanlabs.challenge.movie.application.dto.CreateMovieDTO;
import com.snowmanlabs.challenge.movie.application.dto.MovieDTO;
import com.snowmanlabs.challenge.movie.domain.exception.MovieDomainException;
import com.snowmanlabs.challenge.movie.domain.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CreateMovieUseCase {

    private final MovieRepository movieRepository;

    public MovieDTO execute(final CreateMovieDTO dto) {
        var movie = movieRepository.findByTitleAndFormat(dto.title(), dto.format());
        if (Objects.nonNull(movie))
            throw new MovieDomainException("There is already a movie with same title");

        return MovieDTO.fromDomain(movieRepository.save(dto.toDomain()));
    }

}
