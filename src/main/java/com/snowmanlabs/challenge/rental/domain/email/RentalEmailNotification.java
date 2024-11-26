package com.snowmanlabs.challenge.rental.domain.email;

import com.snowmanlabs.challenge.rental.domain.email.model.RentalEmailMessage;

public interface RentalEmailNotification {

    void sendEmail(RentalEmailMessage message);

}
