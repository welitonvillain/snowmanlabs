package com.snowmanlabs.challenge.rental.domain.event.model;

import com.snowmanlabs.challenge.rental.domain.type.ItemType;

public record RentalEvent(Long rentalId, Long mediaId, ItemType type, String username) {
}
