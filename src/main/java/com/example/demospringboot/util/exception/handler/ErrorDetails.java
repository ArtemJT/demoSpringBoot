package com.example.demospringboot.util.exception.handler;

import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@Builder
public record ErrorDetails(Date timestamp, String message, String details) {

    public static ResponseEntity<ErrorDetails> getResponseEntity(String message, WebRequest request, HttpStatus status) {
        ErrorDetails errorDetails = ErrorDetails.builder()
                .timestamp(new Date())
                .message(message)
                .details(request.getDescription(true))
                .build();

        return new ResponseEntity<>(errorDetails, status);
    }
}
