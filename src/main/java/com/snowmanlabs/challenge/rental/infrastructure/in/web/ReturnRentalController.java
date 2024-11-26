package com.snowmanlabs.challenge.rental.infrastructure.in.web;

import com.snowmanlabs.challenge.rental.application.dto.RentalDTO;
import com.snowmanlabs.challenge.rental.application.dto.ReturnRentalDTO;
import com.snowmanlabs.challenge.rental.application.usecase.ReturnRentalUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/rentals")
@RequiredArgsConstructor
@Tag(name = "Rentals", description = "Operations related to the rentals model.")
public class ReturnRentalController {

    private final ReturnRentalUseCase returnRentalUseCase;

    @PutMapping("/return")
    @Operation(summary = "Return a Rental")
    public RentalDTO execute(@Valid @RequestBody ReturnRentalDTO dto) {
        return returnRentalUseCase.execute(dto);
    }

}
