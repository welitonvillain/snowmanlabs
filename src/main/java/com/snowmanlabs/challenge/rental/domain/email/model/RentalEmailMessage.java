package com.snowmanlabs.challenge.rental.domain.email.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class RentalEmailMessage {

    private String from;
    private String subject;
    private String to;
    private String message;

}
