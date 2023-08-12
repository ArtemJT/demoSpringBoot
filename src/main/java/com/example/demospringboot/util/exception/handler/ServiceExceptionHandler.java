package com.example.demospringboot.util.exception.handler;

import com.example.demospringboot.util.exception.BookingAssignException;
import com.example.demospringboot.util.exception.BookingNotAssignException;
import com.example.demospringboot.util.exception.BookingStatusException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLException;
import java.util.StringJoiner;

import static com.example.demospringboot.util.exception.handler.ErrorDetails.getResponseEntity;

@ControllerAdvice
@Slf4j
public class ServiceExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleEntityNotFoundException(WebRequest request) {
        StringJoiner sj = new StringJoiner("; ");
        request.getParameterMap().forEach((key, value) -> {
            String values = String.join(",", value);
            sj.add(key + "=" + values);
        });

        String message = "Entity not found with parameters: " + sj;
        return getResponseEntity(message, request, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ErrorDetails> handleSqlException(WebRequest request, SQLException e) {
        return getResponseEntity(e.getLocalizedMessage(), request, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookingStatusException.class)
    public ResponseEntity<ErrorDetails> handleBookingStatusException(WebRequest request, BookingStatusException e) {
        String[] headerValues = request.getHeaderValues("orderId");
        String id = headerValues != null ? headerValues[0] : "NULL_ID";
        String message = "Order with id=" + id + " already has status: " + e.getMessage();
        return getResponseEntity(message, request, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookingAssignException.class)
    public ResponseEntity<ErrorDetails> handleBookingAssignException(WebRequest request, BookingAssignException e) {
                String message = "Order with id=" + e.getMessage() + " already assigned";
        return getResponseEntity(message, request, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookingNotAssignException.class)
    public ResponseEntity<ErrorDetails> handleBookingNotAssignException(WebRequest request, BookingNotAssignException e) {
                String message = "Booking with id=" + e.getMessage() + " not assigned yet";
        return getResponseEntity(message, request, HttpStatus.BAD_REQUEST);
    }
}
