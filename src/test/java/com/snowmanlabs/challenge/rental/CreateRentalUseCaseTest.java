package com.snowmanlabs.challenge.rental;

import com.snowmanlabs.challenge.rental.application.dto.CreateRentalDTO;
import com.snowmanlabs.challenge.rental.application.dto.RentalDTO;
import com.snowmanlabs.challenge.rental.application.usecase.CreateRentalUseCase;
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
import com.snowmanlabs.challenge.shared.domain.repository.MediaRepository;
import com.snowmanlabs.challenge.shared.domain.type.Format;
import com.snowmanlabs.challenge.user.domain.entity.User;
import com.snowmanlabs.challenge.user.domain.repository.UserRepository;
import com.snowmanlabs.challenge.user.domain.type.Level;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.*;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateRentalUseCaseTest {

    @InjectMocks
    private CreateRentalUseCase createRentalUseCase;

    @Mock
    private RentalRepository rentalRepository;

    @Mock
    private RentalRepositoryFactory repositoryFactory;

    @Mock
    private UserRepository userRepository;

    @Mock
    private DomainRentalEventPublisher domainRentalEventPublisher;

    @Mock
    private RentalEmailNotification rentalEmailNotification;

    @Mock
    private MediaRepository mediaRepository;

    @Mock
    private Media media;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(createRentalUseCase, "from", "noreply@example.com");
    }

    @Test
    void execute_SuccessfulRental() {
        String username = "weliton.villain";
        Long itemId = 1L;
        ItemType itemType = ItemType.MOVIE;
        Format format = Format.DIGITAL;
        LocalDateTime rentalDate = LocalDateTime.now();

        CreateRentalDTO dto = new CreateRentalDTO(
                itemId,
                itemType,
                username,
                format,
                rentalDate
        );

        User user = new User(
                null,
                username,
                "password",
                "dev.weliton.villain@gmail.com",
                Level.USER
        );

        when(userRepository.findByUsername(username)).thenReturn(user);
        when(repositoryFactory.getRepository(itemType)).thenReturn(mediaRepository);
        when(mediaRepository.findById(itemId)).thenReturn(Optional.of(media));
        when(media.available()).thenReturn(true);
        when(media.id()).thenReturn(itemId);
        when(media.title()).thenReturn("Uma Noite no Museu");
        when(media.format()).thenReturn(format);

        Rental rental = new Rental(
                100L,
                itemId,
                itemType,
                username,
                rentalDate,
                null
        );

        when(rentalRepository.save(any(Rental.class))).thenReturn(rental);

        RentalDTO result = createRentalUseCase.execute(dto);

        assertNotNull(result);
        assertEquals(100L, result.id());
        assertEquals(itemId, result.itemId());
        assertEquals(username, result.username());
        assertEquals(itemType, result.itemType());

        verify(userRepository).findByUsername(username);
        verify(repositoryFactory).getRepository(itemType);
        verify(mediaRepository).findById(itemId);
        verify(media).available();
        verify(rentalRepository).save(any(Rental.class));
        verify(domainRentalEventPublisher).publish(any(RentalEvent.class));
        verify(rentalEmailNotification).sendEmail(any(RentalEmailMessage.class));
    }

    @Test
    void execute_UserNotFound_ShouldThrowException() {
        String username = "user.not.found";
        Long itemId = 1L;
        ItemType itemType = ItemType.MOVIE;
        Format format = Format.DIGITAL;
        LocalDateTime rentalDate = LocalDateTime.now();

        CreateRentalDTO dto = new CreateRentalDTO(
                itemId,
                itemType,
                username,
                format,
                rentalDate
        );

        when(userRepository.findByUsername(username)).thenReturn(null);

        RentalDomainException exception = assertThrows(RentalDomainException.class, () -> {
            createRentalUseCase.execute(dto);
        });

        assertEquals("The user named user.not.found is not registered.", exception.getMessage());

        verify(userRepository).findByUsername(username);
        verifyNoMoreInteractions(repositoryFactory, mediaRepository, rentalRepository, domainRentalEventPublisher, rentalEmailNotification);
    }

    @Test
    void execute_MediaNotFound_ShouldThrowException() {
        String username = "weliton.villain";
        Long itemId = 1L;
        ItemType itemType = ItemType.MOVIE;
        Format format = Format.DIGITAL;
        LocalDateTime rentalDate = LocalDateTime.now();

        CreateRentalDTO dto = new CreateRentalDTO(
                itemId,
                itemType,
                username,
                format,
                rentalDate
        );

        User user = new User(
                null,
                username,
                "password",
                "dev.weliton.villain@gmail.com",
                Level.USER
        );

        when(userRepository.findByUsername(username)).thenReturn(user);
        when(repositoryFactory.getRepository(itemType)).thenReturn(mediaRepository);
        when(mediaRepository.findById(itemId)).thenReturn(Optional.empty());

        RentalDomainException exception = assertThrows(RentalDomainException.class, () -> {
            createRentalUseCase.execute(dto);
        });

        assertEquals("Item not found.", exception.getMessage());

        verify(userRepository).findByUsername(username);
        verify(repositoryFactory).getRepository(itemType);
        verify(mediaRepository).findById(itemId);
        verifyNoMoreInteractions(media, rentalRepository, domainRentalEventPublisher, rentalEmailNotification);
    }

    @Test
    void execute_MediaUnavailable_ShouldThrowException() {
        String username = "weliton.villain";
        Long itemId = 1L;
        ItemType itemType = ItemType.MOVIE;
        Format format = Format.DIGITAL;
        LocalDateTime rentalDate = LocalDateTime.now();

        CreateRentalDTO dto = new CreateRentalDTO(
                itemId,
                itemType,
                username,
                format,
                rentalDate
        );

        User user = new User(
                null,
                username,
                "password",
                "dev.weliton.villain@gmail.com",
                Level.USER
        );

        when(userRepository.findByUsername(username)).thenReturn(user);
        when(repositoryFactory.getRepository(itemType)).thenReturn(mediaRepository);
        when(mediaRepository.findById(itemId)).thenReturn(Optional.of(media));
        when(media.available()).thenReturn(false);

        RentalDomainException exception = assertThrows(RentalDomainException.class, () -> {
            createRentalUseCase.execute(dto);
        });

        assertEquals("The item you want to rent is unavailable.", exception.getMessage());

        verify(userRepository).findByUsername(username);
        verify(repositoryFactory).getRepository(itemType);
        verify(mediaRepository).findById(itemId);
        verify(media).available();
        verifyNoMoreInteractions(rentalRepository, domainRentalEventPublisher, rentalEmailNotification);
    }
}
