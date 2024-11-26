package com.snowmanlabs.challenge.rental.application.dto;

import com.snowmanlabs.challenge.rental.domain.type.ItemType;
import com.snowmanlabs.challenge.shared.domain.type.Format;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CreateRentalDTO(
        @NotNull Long itemId,
        @NotNull ItemType itemType,
        @NotEmpty String username,
        @NotNull Format format,
        @NotNull LocalDateTime rentalDate
){
}
