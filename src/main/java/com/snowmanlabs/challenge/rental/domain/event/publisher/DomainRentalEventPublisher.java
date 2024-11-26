package com.snowmanlabs.challenge.rental.domain.event.publisher;

import com.snowmanlabs.challenge.rental.domain.event.model.RentalEvent;

public interface DomainRentalEventPublisher {

    void publish(RentalEvent event);

}
