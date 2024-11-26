package com.snowmanlabs.challenge.movie.domain.repository;

import com.snowmanlabs.challenge.movie.domain.entity.Movie;
import com.snowmanlabs.challenge.shared.domain.pagination.PageResult;
import com.snowmanlabs.challenge.shared.domain.pagination.PageQuery;
import com.snowmanlabs.challenge.shared.domain.type.Format;

public interface MovieRepository {

    Movie save(Movie user);
    void delete(Long id);
    Movie update(Long id, Movie movie);
    Movie findById(Long id);
    Movie findByTitleAndFormat(String title, Format format);
    PageResult<Movie> findAll(PageQuery pageQuery);
    void setMovieAvailable(Long id);

}
