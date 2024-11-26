package com.snowmanlabs.challenge.shared.domain.pagination;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageQuery {

    private final int page;
    private final int size;
    private final String sortBy;
    private final String direction;

    public PageQuery(int page, int size, String sortBy, String direction) {
        if (page < 0) throw new IllegalArgumentException("Page index must not be less than zero!");
        if (size <= 0) throw new IllegalArgumentException("Page size must be greater than zero!");
        this.page = page;
        this.size = size;
        this.sortBy = sortBy;
        this.direction = direction;
    }

    public static PageQuery of(int page, int size, String sortBy, String direction) {
        return new PageQuery(page, size, sortBy, direction);
    }

}
