package com.snowmanlabs.challenge.rental.infrastructure.out.persistence.repository;

import com.snowmanlabs.challenge.rental.domain.entity.Rental;
import com.snowmanlabs.challenge.rental.domain.repository.RentalRepository;
import com.snowmanlabs.challenge.rental.infrastructure.out.persistence.entity.RentalEntity;
import com.snowmanlabs.challenge.shared.domain.pagination.PageQuery;
import com.snowmanlabs.challenge.shared.domain.pagination.PageResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RentalRepositoryImpl implements RentalRepository {

    private final JpaRentalRepository jpaRentalRepository;

    @Override
    public Optional<Rental> findById(final Long id) {
        var entity = jpaRentalRepository.findById(id);
        return entity.map(RentalEntity::toDomain);
    }

    @Override
    public Rental save(final Rental rental) {
        var entity = RentalEntity.toEntity(rental);
        return jpaRentalRepository.save(entity).toDomain();
    }

    @Override
    public void updateReturnDate(final Long id, final LocalDateTime returnDate) {
        jpaRentalRepository.updateReturnDate(returnDate, id);
    }

    @Override
    public PageResult<Rental> findAll(PageQuery pageQuery) {
        Pageable pageable = PageRequest.of(
                pageQuery.getPage(),
                pageQuery.getSize(),
                Sort.by(
                        Sort.Direction.fromString(pageQuery.getDirection()),
                        pageQuery.getSortBy()
                ));

        Page<RentalEntity> rentalEntityPage = jpaRentalRepository.findAll(pageable);

        List<Rental> rentals = rentalEntityPage.getContent()
                .stream()
                .map(RentalEntity::toDomain)
                .toList();

        return new PageResult<>(
                rentals,
                rentalEntityPage.getNumber(),
                rentalEntityPage.getSize(),
                rentalEntityPage.getTotalElements()
        );
    }
}
