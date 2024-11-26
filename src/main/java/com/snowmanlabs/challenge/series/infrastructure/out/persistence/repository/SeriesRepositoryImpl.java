package com.snowmanlabs.challenge.series.infrastructure.out.persistence.repository;

import com.snowmanlabs.challenge.series.domain.entity.Series;
import com.snowmanlabs.challenge.series.domain.exception.SeriesNotFoundException;
import com.snowmanlabs.challenge.series.domain.repository.SeriesRepository;
import com.snowmanlabs.challenge.series.infrastructure.out.persistence.entity.SeriesEntity;
import com.snowmanlabs.challenge.shared.domain.pagination.PageQuery;
import com.snowmanlabs.challenge.shared.domain.pagination.PageResult;
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
public class SeriesRepositoryImpl implements SeriesRepository {

    private final JpaSeriesRepository jpaSeriesRepository;

    @Override
    public Series save(final Series series) {
        var entity = SeriesEntity.toEntity(series);
        return jpaSeriesRepository.save(entity).toDomain();
    }

    @Override
    public void delete(final Long id) {
        jpaSeriesRepository.deleteById(id);
    }

    @Override
    public Series update(final Long id, final Series series) {
        return jpaSeriesRepository.findById(id)
                .map(entity -> {
                    entity.setTitle(series.title());
                    entity.setYear(series.year());
                    entity.setAvailable(series.available());
                    entity.setGenre(series.genre());
                    entity.setSeason(series.season());
                    entity.setEpisodes(series.episodes());
                    return jpaSeriesRepository.save(entity).toDomain();
                })
                .orElseThrow(() -> new SeriesNotFoundException(
                        String.format("The series with id %s was not found.", id))
                );
    }

    @Override
    public Series findById(final Long id) {
        var entity = jpaSeriesRepository.findById(id);
        return entity.map(SeriesEntity::toDomain).orElse(null);
    }

    @Override
    public Series findByTitleAndFormat(final String title, final Format format) {
        var entity = jpaSeriesRepository.findByTitleAndFormat(title, format);
        return entity.map(SeriesEntity::toDomain).orElse(null);
    }

    @Override
    public PageResult<Series> findAll(PageQuery pageQuery) {
        Pageable pageable = PageRequest.of(
                pageQuery.getPage(),
                pageQuery.getSize(),
                Sort.by(
                        Sort.Direction.fromString(pageQuery.getDirection()),
                        pageQuery.getSortBy()
                ));

        Page<SeriesEntity> seriesEntityPage = jpaSeriesRepository.findAll(pageable);

        List<Series> movies = seriesEntityPage.getContent()
                .stream()
                .map(SeriesEntity::toDomain)
                .toList();

        return new PageResult<>(
                movies,
                seriesEntityPage.getNumber(),
                seriesEntityPage.getSize(),
                seriesEntityPage.getTotalElements()
        );
    }

    @Override
    public void setSeriesAvailable(Long id) {
        jpaSeriesRepository.setSeriesAvailable(id);
    }
}
