package com.snowmanlabs.challenge.rental.infrastructure.out.persistence.repository;

import com.snowmanlabs.challenge.rental.domain.exception.RentalDomainException;
import com.snowmanlabs.challenge.rental.domain.type.ItemType;
import com.snowmanlabs.challenge.shared.domain.repository.MediaRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class RentalRepositoryFactory {

    private final Map<ItemType, MediaRepository> repositories;

    public RentalRepositoryFactory(final MovieRentalRepository movieRentalRepository, final SeriesRentalRepository seriesRentalRepository) {
        repositories = Map.of(
                ItemType.MOVIE, movieRentalRepository,
                ItemType.SERIES, seriesRentalRepository
        );
    }

    public MediaRepository getRepository(final ItemType type) {
        var repository = repositories.get(type);
        if (repository == null)
            throw new RentalDomainException("Item type is invalid: " + type);

        return repository;
    }

}
