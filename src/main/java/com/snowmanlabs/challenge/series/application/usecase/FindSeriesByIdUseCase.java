package com.snowmanlabs.challenge.series.application.usecase;

import com.snowmanlabs.challenge.series.application.dto.SeriesDTO;
import com.snowmanlabs.challenge.series.domain.entity.Series;
import com.snowmanlabs.challenge.series.domain.exception.SeriesNotFoundException;
import com.snowmanlabs.challenge.series.domain.repository.SeriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FindSeriesByIdUseCase {

    private final SeriesRepository seriesRepository;

    public SeriesDTO execute(final Long id) {
        Series domainSeries = seriesRepository.findById(id);

        if (Objects.isNull(domainSeries))
            throw new SeriesNotFoundException(String.format("The series with id %s was not found.", id));

        return SeriesDTO.fromDomain(domainSeries);
    }

}
