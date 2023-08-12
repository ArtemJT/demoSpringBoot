package com.example.demospringboot.util.exception;

/**
 * @author Artem Kovalov on 12.08.2023
 */
public class BookingStatusException extends RuntimeException {

    public BookingStatusException(String message) {
        super(message);
    }
}
