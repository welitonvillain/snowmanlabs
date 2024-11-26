package com.snowmanlabs.challenge.movie.domain.exception;

public class MovieUnavailableException extends RuntimeException {
    public MovieUnavailableException(String message) {
        super(message);
    }
}
