package com.example.demospringboot.util.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import static com.example.demospringboot.util.exception.handler.ErrorDetails.getResponseEntity;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorDetails> handleMethodArgumentTypeMismatchException(WebRequest request, MethodArgumentTypeMismatchException e) {
        return getResponseEntity(e.getLocalizedMessage(), request, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorDetails> handleMissingServletRequestParameterException(WebRequest request, MissingServletRequestParameterException e) {
        return getResponseEntity(e.getLocalizedMessage(), request, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NumberFormatException.class)
    protected ResponseEntity<ErrorDetails> handleNumberFormatException(WebRequest request, NumberFormatException e) {
        return getResponseEntity(e.getLocalizedMessage(), request, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<ErrorDetails> handleNumberFormatException(WebRequest request, HttpMessageNotReadableException e) {
        return getResponseEntity(e.getLocalizedMessage(), request, HttpStatus.BAD_REQUEST);
    }

}
