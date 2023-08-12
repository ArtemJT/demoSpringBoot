package com.example.demospringboot.util.exception;

/**
 * @author Artem Kovalov on 12.08.2023
 */
public class RequestAssignException extends RuntimeException {

    public RequestAssignException(String message) {
        super(message);
    }
}
