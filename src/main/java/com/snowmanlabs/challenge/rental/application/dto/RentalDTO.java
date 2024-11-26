package com.snowmanlabs.challenge.rental.application.dto;

import com.snowmanlabs.challenge.rental.domain.entity.Rental;
import com.snowmanlabs.challenge.rental.domain.type.ItemType;

import java.time.LocalDateTime;

public record RentalDTO(
        Long id, Long itemId, ItemType itemType, String username, LocalDateTime rentalDate, LocalDateTime returnDate
) {

    public static RentalDTO fromDomain(final Rental rental) {
        return new RentalDTO(rental.id(), rental.itemId(), rental.itemType(), rental.username(), rental.rentalDate(), rental.returnDate());
    }

    public static RentalDTO fromDomain(final LocalDateTime returnDate, final Rental rental) {
        return new RentalDTO(rental.id(), rental.itemId(), rental.itemType(), rental.username(), rental.rentalDate(), returnDate);
    }

}
