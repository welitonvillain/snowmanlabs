package com.snowmanlabs.challenge.shared.domain.entity;

import com.snowmanlabs.challenge.shared.domain.type.Format;
import com.snowmanlabs.challenge.shared.domain.type.Genre;

public interface Media {
        Long id();
        String title();
        int year();
        Format format();
        Genre genre();
        boolean available();
}
