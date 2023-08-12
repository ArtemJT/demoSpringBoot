package com.example.demospringboot.util.exception;

/**
 * @author Artem Kovalov on 12.08.2023
 */
public class BookingNotAssignException extends RuntimeException {

    public BookingNotAssignException(String message) {
        super(message);
    }
}
