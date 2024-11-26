package com.snowmanlabs.challenge.series.application.usecase;

import com.snowmanlabs.challenge.series.domain.exception.SeriesNotFoundException;
import com.snowmanlabs.challenge.series.domain.repository.SeriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DeleteSeriesUseCase {

    private final SeriesRepository seriesRepository;

    public void execute(final Long id) {
        var series = seriesRepository.findById(id);
        if (Objects.isNull(series)) {
            throw new SeriesNotFoundException(String.format("The series with id %s was not found.", id));
        }

        seriesRepository.delete(id);
    }

}
