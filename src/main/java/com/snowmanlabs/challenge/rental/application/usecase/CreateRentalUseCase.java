package com.snowmanlabs.challenge.rental.application.usecase;

import com.snowmanlabs.challenge.rental.application.dto.CreateRentalDTO;
import com.snowmanlabs.challenge.rental.application.dto.RentalDTO;
import com.snowmanlabs.challenge.rental.domain.email.RentalEmailNotification;
import com.snowmanlabs.challenge.rental.domain.email.model.RentalEmailMessage;
import com.snowmanlabs.challenge.rental.domain.entity.Rental;
import com.snowmanlabs.challenge.rental.domain.event.model.RentalEvent;
import com.snowmanlabs.challenge.rental.domain.event.publisher.DomainRentalEventPublisher;
import com.snowmanlabs.challenge.rental.domain.exception.RentalDomainException;
import com.snowmanlabs.challenge.rental.domain.repository.RentalRepository;
import com.snowmanlabs.challenge.rental.domain.type.ItemType;
import com.snowmanlabs.challenge.rental.infrastructure.out.persistence.repository.RentalRepositoryFactory;
import com.snowmanlabs.challenge.shared.domain.entity.Media;
import com.snowmanlabs.challenge.user.domain.entity.User;
import com.snowmanlabs.challenge.user.domain.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CreateRentalUseCase {

    @Value("${spring.mail.username}")
    private String from;

    private final RentalRepository rentalRepository;
    private final RentalRepositoryFactory repositoryFactory;
    private final UserRepository userRepository;
    private final DomainRentalEventPublisher domainRentalEventPublisher;
    private final RentalEmailNotification rentalEmailNotification;

    @Transactional
    public RentalDTO execute(final CreateRentalDTO dto) {
        var user = findUser(dto);
        var media = findMediaAndCheckAvailability(dto);
        var rental = persistRental(user, media, dto.itemType());
        notifyRental(user, media, rental, dto.itemType());
        sendConfirmationEmail(user, media);
        return RentalDTO.fromDomain(rental);
    }

    private User findUser(final CreateRentalDTO dto) {
        var user = userRepository.findByUsername(dto.username());
        if (user == null)
            throw new RentalDomainException(String.format("The user named %s is not registered.", dto.username()));

        return user;
    }

    private Media findMediaAndCheckAvailability(final CreateRentalDTO dto) {
        var repository = repositoryFactory.getRepository(dto.itemType());
        var media = repository.findById(dto.itemId())
                .orElseThrow(() -> new RentalDomainException("Item not found."));

        if (!media.available())
            throw new RentalDomainException("The item you want to rent is unavailable.");

        return media;
    }

    private Rental persistRental(final User user, final Media media, final ItemType type) {
        return rentalRepository.save(new Rental(
                null,
                media.id(),
                type,
                user.username(),
                LocalDateTime.now(),
                null));
    }

    private void notifyRental(final User user, final Media media, final Rental rental, final ItemType type) {
        domainRentalEventPublisher.publish(new RentalEvent(rental.id(), media.id(), type, user.username()));
    }

    private void sendConfirmationEmail(final User user, final Media media) {
        rentalEmailNotification.sendEmail(RentalEmailMessage.builder()
                .from(this.from)
                .to(user.email())
                .subject("Rental confirmation")
                .message(String.format("Confirming rental of the film/series %s in format %s", media.title(), media.format()))
                .build());
    }

}
