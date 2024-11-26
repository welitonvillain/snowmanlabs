package com.snowmanlabs.challenge.series.domain.repository;

import com.snowmanlabs.challenge.series.domain.entity.Series;
import com.snowmanlabs.challenge.shared.domain.pagination.PageQuery;
import com.snowmanlabs.challenge.shared.domain.pagination.PageResult;
import com.snowmanlabs.challenge.shared.domain.type.Format;

public interface SeriesRepository {

    Series save(Series user);
    void delete(Long id);
    Series update(Long id, Series movie);
    Series findById(Long id);
    Series findByTitleAndFormat(String title, Format format);
    PageResult<Series> findAll(PageQuery pageQuery);
    void setSeriesAvailable(Long id);

}
