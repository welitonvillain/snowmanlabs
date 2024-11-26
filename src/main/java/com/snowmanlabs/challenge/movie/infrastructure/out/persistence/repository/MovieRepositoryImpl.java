package com.snowmanlabs.challenge.movie.infrastructure.out.persistence.repository;

import com.snowmanlabs.challenge.movie.domain.entity.Movie;
import com.snowmanlabs.challenge.movie.domain.exception.MovieNotFoundException;
import com.snowmanlabs.challenge.movie.domain.repository.MovieRepository;
import com.snowmanlabs.challenge.movie.infrastructure.out.persistence.entity.MovieEntity;
import com.snowmanlabs.challenge.shared.domain.pagination.PageResult;
import com.snowmanlabs.challenge.shared.domain.pagination.PageQuery;
import com.snowmanlabs.challenge.shared.domain.type.Format;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MovieRepositoryImpl implements MovieRepository {

    private final JpaMovieRepository jpaMovieRepository;

    @Override
    public Movie save(final Movie movie) {
        var entity = MovieEntity.toEntity(movie);
        return jpaMovieRepository.save(entity).toDomain();
    }

    @Override
    public void delete(final Long id) {
        jpaMovieRepository.deleteById(id);
    }

    @Override
    public Movie update(final Long id, final Movie movie) {
        return jpaMovieRepository.findById(id)
                .map(entity -> {
                    entity.setTitle(movie.title());
                    entity.setYear(movie.year());
                    entity.setAvailable(movie.available());
                    entity.setGenre(movie.genre());
                    entity.setDuration(movie.duration());
                    entity.setFormat(movie.format());
                    return jpaMovieRepository.save(entity).toDomain();
                })
                .orElseThrow(() -> new MovieNotFoundException(
                        String.format("The movie with id %s was not found.", id))
                );
    }

    @Override
    public Movie findById(final Long id) {
        var entity = jpaMovieRepository.findById(id);
        return entity.map(MovieEntity::toDomain).orElse(null);
    }

    @Override
    public Movie findByTitleAndFormat(final String title, final Format format) {
        var entity = jpaMovieRepository.findByTitleAndFormat(title, format);
        return entity.map(MovieEntity::toDomain).orElse(null);
    }

    @Override
    public PageResult<Movie> findAll(PageQuery pageQuery) {
        Pageable pageable = PageRequest.of(
                pageQuery.getPage(),
                pageQuery.getSize(),
                Sort.by(
                        Sort.Direction.fromString(pageQuery.getDirection()),
                        pageQuery.getSortBy()
                ));

        Page<MovieEntity> movieEntityPage = jpaMovieRepository.findAll(pageable);

        List<Movie> movies = movieEntityPage.getContent()
                .stream()
                .map(MovieEntity::toDomain)
                .toList();

        return new PageResult<>(
                movies,
                movieEntityPage.getNumber(),
                movieEntityPage.getSize(),
                movieEntityPage.getTotalElements()
        );
    }

    @Override
    public void setMovieAvailable(Long id) {
        jpaMovieRepository.setMovieAvailable(id);
    }
}
