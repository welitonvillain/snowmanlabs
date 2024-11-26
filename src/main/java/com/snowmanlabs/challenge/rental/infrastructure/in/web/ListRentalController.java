package com.snowmanlabs.challenge.rental.infrastructure.in.web;

import com.snowmanlabs.challenge.rental.application.dto.RentalDTO;
import com.snowmanlabs.challenge.rental.application.usecase.ListRentalUseCase;
import com.snowmanlabs.challenge.shared.domain.pagination.PageResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/rentals")
@RequiredArgsConstructor
@Tag(name = "Rentals", description = "Operations related to the rentals model.")
public class ListRentalController {

    private final ListRentalUseCase listRentalUseCase;

    @GetMapping
    @Operation(summary = "List all rental with pagination and sorting")
    public PageResult<RentalDTO> execute(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "title") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ){
        return listRentalUseCase.execute(page, size, sortBy, direction);
    }

}
