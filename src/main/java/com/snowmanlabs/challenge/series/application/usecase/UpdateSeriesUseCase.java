package com.snowmanlabs.challenge.series.application.usecase;

import com.snowmanlabs.challenge.series.application.dto.SeriesDTO;
import com.snowmanlabs.challenge.series.application.dto.UpdateSeriesDTO;
import com.snowmanlabs.challenge.series.domain.exception.SeriesNotFoundException;
import com.snowmanlabs.challenge.series.domain.repository.SeriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UpdateSeriesUseCase {

    private final SeriesRepository seriesRepository;

    public SeriesDTO execute(final UpdateSeriesDTO dto) {
        var domainSeries = seriesRepository.findById(dto.id());

        if (Objects.isNull(domainSeries))
            throw new SeriesNotFoundException(String.format("The series with id %s was not found.", dto.id()));

        return SeriesDTO.fromDomain(seriesRepository.update(dto.id(), dto.toDomain()));
    }

}
