package com.snowmanlabs.challenge.rental.infrastructure.out.event.publisher;

import com.snowmanlabs.challenge.rental.domain.event.model.RentalEvent;
import com.snowmanlabs.challenge.rental.domain.event.publisher.DomainRentalEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RentalEventPublisher implements DomainRentalEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public void publish(RentalEvent event) {
        applicationEventPublisher.publishEvent(event);
    }

}
