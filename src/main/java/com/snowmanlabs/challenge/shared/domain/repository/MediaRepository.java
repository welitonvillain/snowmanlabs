package com.snowmanlabs.challenge.shared.domain.repository;

import com.snowmanlabs.challenge.shared.domain.entity.Media;

import java.util.Optional;

public interface MediaRepository {

    Optional<Media> findById(Long id);
    void setMediaAvailable(Long id);

}
