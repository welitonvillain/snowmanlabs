package com.snowmanlabs.challenge.shared.config;

import com.snowmanlabs.challenge.movie.domain.exception.MovieDomainException;
import com.snowmanlabs.challenge.movie.domain.exception.MovieNotFoundException;
import com.snowmanlabs.challenge.movie.domain.exception.MovieUnavailableException;
import com.snowmanlabs.challenge.rental.domain.exception.RentalDomainException;
import com.snowmanlabs.challenge.rental.domain.exception.RentalNotFoundException;
import com.snowmanlabs.challenge.series.domain.exception.SeriesDomainException;
import com.snowmanlabs.challenge.series.domain.exception.SeriesNotFoundException;
import com.snowmanlabs.challenge.user.domain.exception.UserDomainException;
import com.snowmanlabs.challenge.user.domain.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CustomControllerAdvice {

    @ExceptionHandler({
            MovieNotFoundException.class,
            RentalNotFoundException.class,
            UserNotFoundException.class,
            SeriesNotFoundException.class,
            MovieUnavailableException.class
    })
    public ResponseEntity<Map<String, Object>> handleNotFoundException(Exception e) {
        Map<String, Object> errors = new HashMap<>();
        errors.put("status", HttpStatus.NOT_FOUND.value());
        errors.put("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
    }

    @ExceptionHandler({
            MovieDomainException.class,
            RentalDomainException.class,
            UserDomainException.class,
            SeriesDomainException.class
    })
    public ResponseEntity<Map<String, Object>> handleDomainException(Exception e) {
        Map<String, Object> errors = new HashMap<>();
        errors.put("status", HttpStatus.BAD_REQUEST.value());
        errors.put("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();

        e.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
