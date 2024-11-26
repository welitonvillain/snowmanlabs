package com.snowmanlabs.challenge.series.application.usecase;

import com.snowmanlabs.challenge.series.application.dto.SeriesDTO;
import com.snowmanlabs.challenge.series.domain.entity.Series;
import com.snowmanlabs.challenge.series.domain.repository.SeriesRepository;
import com.snowmanlabs.challenge.shared.domain.pagination.PageQuery;
import com.snowmanlabs.challenge.shared.domain.pagination.PageResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListSeriesUseCase {

    private final SeriesRepository seriesRepository;

    public PageResult<SeriesDTO> execute(final int page, final int size, final String sortBy, final String direction) {
        PageResult<Series> seriesPageResult = seriesRepository.findAll(PageQuery.of(page, size, sortBy, direction));

        List<SeriesDTO> seriesDTOS = seriesPageResult.getContent()
                .stream()
                .map(SeriesDTO::fromDomain)
                .toList();

        return new PageResult<>(
                seriesDTOS,
                seriesPageResult.getPage(),
                seriesPageResult.getSize(),
                seriesPageResult.getTotalElements()
        );
    }
}
