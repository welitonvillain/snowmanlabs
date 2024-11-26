package com.snowmanlabs.challenge.rental.infrastructure.out.persistence.repository;

import com.snowmanlabs.challenge.rental.infrastructure.out.persistence.entity.RentalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface JpaRentalRepository extends JpaRepository<RentalEntity, Long> {

    List<RentalEntity> findByUsername(String username);

    @Modifying
    @Query(value = "UPDATE rentals SET return_date = :returnDate WHERE id = :id", nativeQuery = true)
    void updateReturnDate(@Param("returnDate") LocalDateTime returnDate, @Param("id") Long id);

}
