package com.snowmanlabs.challenge.rental.domain.entity;

import com.snowmanlabs.challenge.rental.domain.type.ItemType;

import java.time.LocalDateTime;

public record Rental(
        Long id, Long itemId, ItemType itemType, String username, LocalDateTime rentalDate, LocalDateTime returnDate
) {
}
