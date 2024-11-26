package com.snowmanlabs.challenge.movie.infrastructure.out.persistence.repository;

import com.snowmanlabs.challenge.movie.infrastructure.out.persistence.entity.MovieEntity;
import com.snowmanlabs.challenge.shared.domain.type.Format;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaMovieRepository extends JpaRepository<MovieEntity, Long> {

    Optional<MovieEntity> findByTitleAndFormat(String title, Format format);

    @Modifying
    @Query(value = "UPDATE movies SET available = true WHERE id = :id", nativeQuery = true)
    void setMovieAvailable(@Param("id") Long id);

}
