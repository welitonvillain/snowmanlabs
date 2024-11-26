package com.snowmanlabs.challenge.rental.application.usecase;

import com.snowmanlabs.challenge.rental.application.dto.RentalDTO;
import com.snowmanlabs.challenge.rental.domain.entity.Rental;
import com.snowmanlabs.challenge.rental.domain.repository.RentalRepository;
import com.snowmanlabs.challenge.shared.domain.pagination.PageQuery;
import com.snowmanlabs.challenge.shared.domain.pagination.PageResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListRentalUseCase {

    private final RentalRepository rentalRepository;

    public PageResult<RentalDTO> execute(final int page, final int size, final String sortBy, final String direction) {
        PageResult<Rental> rentalPageResult = rentalRepository.findAll(PageQuery.of(page, size, sortBy, direction));

        List<RentalDTO> rentalDTOS = rentalPageResult.getContent()
                .stream()
                .map(RentalDTO::fromDomain)
                .toList();

        return new PageResult<>(
                rentalDTOS,
                rentalPageResult.getPage(),
                rentalPageResult.getSize(),
                rentalPageResult.getTotalElements()
        );
    }
}
