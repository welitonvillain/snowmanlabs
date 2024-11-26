package com.snowmanlabs.challenge.rental.application.dto;

import com.snowmanlabs.challenge.shared.domain.type.Format;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ReturnRentalDTO(
        @NotNull Long id,
        @NotNull Format format,
        @NotNull LocalDateTime returnDate
){
}
