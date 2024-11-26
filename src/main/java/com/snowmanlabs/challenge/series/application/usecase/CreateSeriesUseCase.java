package com.snowmanlabs.challenge.series.application.usecase;

import com.snowmanlabs.challenge.movie.domain.exception.MovieDomainException;
import com.snowmanlabs.challenge.series.application.dto.CreateSeriesDTO;
import com.snowmanlabs.challenge.series.application.dto.SeriesDTO;
import com.snowmanlabs.challenge.series.domain.repository.SeriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CreateSeriesUseCase {

    private final SeriesRepository seriesRepository;

    public SeriesDTO execute(final CreateSeriesDTO dto) {
        var series = seriesRepository.findByTitleAndFormat(dto.title(), dto.format());
        if (Objects.nonNull(series))
            throw new MovieDomainException("There is already a series with same title");

        return SeriesDTO.fromDomain(seriesRepository.save(dto.toDomain()));
    }

}
