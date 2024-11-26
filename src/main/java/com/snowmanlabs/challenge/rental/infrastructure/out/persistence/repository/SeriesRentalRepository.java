package com.snowmanlabs.challenge.rental.infrastructure.out.persistence.repository;

import com.snowmanlabs.challenge.series.domain.repository.SeriesRepository;
import com.snowmanlabs.challenge.shared.domain.entity.Media;
import com.snowmanlabs.challenge.shared.domain.repository.MediaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SeriesRentalRepository implements MediaRepository {

    private final SeriesRepository seriesRepository;

    @Override
    public Optional<Media> findById(Long id) {
        var series = seriesRepository.findById(id);
        return Objects.nonNull(series) ? Optional.of(series) : Optional.empty();
    }

    @Override
    public void setMediaAvailable(Long id) {
        seriesRepository.setSeriesAvailable(id);
    }

}
