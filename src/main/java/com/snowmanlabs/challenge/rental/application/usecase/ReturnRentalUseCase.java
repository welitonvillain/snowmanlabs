package com.snowmanlabs.challenge.rental.application.usecase;

import com.snowmanlabs.challenge.rental.application.dto.RentalDTO;
import com.snowmanlabs.challenge.rental.application.dto.ReturnRentalDTO;
import com.snowmanlabs.challenge.rental.domain.exception.RentalDomainException;
import com.snowmanlabs.challenge.rental.domain.exception.RentalNotFoundException;
import com.snowmanlabs.challenge.rental.domain.repository.RentalRepository;
import com.snowmanlabs.challenge.rental.domain.type.ItemType;
import com.snowmanlabs.challenge.rental.infrastructure.out.persistence.repository.RentalRepositoryFactory;
import com.snowmanlabs.challenge.shared.domain.entity.Media;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReturnRentalUseCase {

    @Value("${spring.mail.username}")
    private String from;

    private final RentalRepository rentalRepository;
    private final RentalRepositoryFactory repositoryFactory;

    @Transactional
    public RentalDTO execute(final ReturnRentalDTO dto) {
        var rental = rentalRepository.findById(dto.id())
                .orElseThrow(() -> new RentalNotFoundException("The reported rent does not exist."));

        var media = findMedia(rental.itemType(), rental.itemId());
        updateRental(media.id(), LocalDateTime.now());
        setMediaAvailable(rental.itemType(), media.id());
        return RentalDTO.fromDomain(dto.returnDate(), rental);
    }

    private Media findMedia(final ItemType type, final Long mediaId) {
        var repository = repositoryFactory.getRepository(type);
        return repository.findById(mediaId)
                .orElseThrow(() -> new RentalDomainException("Item not found."));
    }

    private void updateRental(final Long id, final LocalDateTime returnDate) {
        rentalRepository.updateReturnDate(id, returnDate);
    }

    private void setMediaAvailable(final ItemType type, final Long mediaId) {
        var repository = repositoryFactory.getRepository(type);
        repository.setMediaAvailable(mediaId);
    }

}
