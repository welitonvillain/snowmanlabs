package com.snowmanlabs.challenge.rental.infrastructure.in.web;

import com.snowmanlabs.challenge.rental.application.dto.CreateRentalDTO;
import com.snowmanlabs.challenge.rental.application.dto.RentalDTO;
import com.snowmanlabs.challenge.rental.application.usecase.CreateRentalUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/rentals")
@RequiredArgsConstructor
@Tag(name = "Rentals", description = "Operations related to the rentals model.")
public class CreateRentalController {

    private final CreateRentalUseCase createRentalUseCase;

    @PostMapping("/rent")
    @Operation(summary = "Create a Rental")
    public RentalDTO execute(@Valid @RequestBody CreateRentalDTO dto) {
        return createRentalUseCase.execute(dto);
    }

}
