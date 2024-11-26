package com.snowmanlabs.challenge.movie.application.usecase;

import com.snowmanlabs.challenge.movie.application.dto.MovieDTO;
import com.snowmanlabs.challenge.movie.domain.entity.Movie;
import com.snowmanlabs.challenge.movie.domain.repository.MovieRepository;
import com.snowmanlabs.challenge.shared.domain.pagination.PageResult;
import com.snowmanlabs.challenge.shared.domain.pagination.PageQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListMoviesUseCase {

    private final MovieRepository movieRepository;

    public PageResult<MovieDTO> execute(final int page, final int size, final String sortBy, final String direction) {
        PageResult<Movie> moviePageResult = movieRepository.findAll(PageQuery.of(page, size, sortBy, direction));

        List<MovieDTO> movieDTOs = moviePageResult.getContent()
                .stream()
                .map(MovieDTO::fromDomain)
                .toList();

        return new PageResult<>(
                movieDTOs,
                moviePageResult.getPage(),
                moviePageResult.getSize(),
                moviePageResult.getTotalElements()
        );
    }
}
