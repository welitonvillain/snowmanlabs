package com.snowmanlabs.challenge.series.infrastructure.out.persistence.repository;

import com.snowmanlabs.challenge.series.infrastructure.out.persistence.entity.SeriesEntity;
import com.snowmanlabs.challenge.shared.domain.type.Format;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaSeriesRepository extends JpaRepository<SeriesEntity, Long> {

    Optional<SeriesEntity> findByTitleAndFormat(String title, Format format);

    @Modifying
    @Query(value = "UPDATE series SET available = true WHERE id = :id", nativeQuery = true)
    void setSeriesAvailable(@Param("id") Long id);

}
