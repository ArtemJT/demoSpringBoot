package com.example.demospringboot.util.exception.handler;

import com.example.demospringboot.util.exception.RequestAssignException;
import com.example.demospringboot.util.exception.RequestNotAssignException;
import com.example.demospringboot.util.exception.RequestStatusException;
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

    @ExceptionHandler(RequestStatusException.class)
    public ResponseEntity<ErrorDetails> handleRequestStatusException(WebRequest request, RequestStatusException e) {
        String[] headerValues = request.getHeaderValues("orderId");
        String id = headerValues != null ? headerValues[0] : "NULL_ID";
        String message = "Order with id=" + id + " already has status: " + e.getMessage();
        return getResponseEntity(message, request, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RequestAssignException.class)
    public ResponseEntity<ErrorDetails> handleRequestAssignException(WebRequest request, RequestAssignException e) {
                String message = "Order with id=" + e.getMessage() + " already assigned";
        return getResponseEntity(message, request, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RequestNotAssignException.class)
    public ResponseEntity<ErrorDetails> handleRequestNotAssignException(WebRequest request, RequestNotAssignException e) {
                String message = "Request with id=" + e.getMessage() + " not assigned yet";
        return getResponseEntity(message, request, HttpStatus.BAD_REQUEST);
    }
}
