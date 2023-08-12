package com.example.demospringboot.util.exception;

/**
 * @author Artem Kovalov on 12.08.2023
 */
public class RequestStatusException extends RuntimeException {

    public RequestStatusException(String message) {
        super(message);
    }
}
