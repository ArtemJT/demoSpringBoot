package com.example.demospringboot.util.exception.handler;

import com.example.demospringboot.util.exception.OrderStatusException;
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

    @ExceptionHandler(OrderStatusException.class)
    public ResponseEntity<ErrorDetails> handleOrderStatusException(WebRequest request, OrderStatusException e) {
        String[] headerValues = request.getHeaderValues("orderId");
        String id = headerValues != null ? headerValues[0] : "NULL_ID";
        String message = "Order with id=" + id + " already has status: " + e.getMessage();
        return getResponseEntity(message, request, HttpStatus.BAD_REQUEST);
    }
}
