package com.snowmanlabs.challenge.rental.domain.repository;

import com.snowmanlabs.challenge.rental.domain.entity.Rental;
import com.snowmanlabs.challenge.shared.domain.pagination.PageQuery;
import com.snowmanlabs.challenge.shared.domain.pagination.PageResult;

import java.time.LocalDateTime;
import java.util.Optional;

public interface RentalRepository {

    Optional<Rental> findById(Long id);
    Rental save(Rental rental);
    void updateReturnDate(Long id, LocalDateTime returnDate);
    PageResult<Rental> findAll(PageQuery pageQuery);

}
