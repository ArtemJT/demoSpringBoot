package com.example.demospringboot.util.exception;

/**
 * @author Artem Kovalov on 12.08.2023
 */
public class OrderStatusException extends RuntimeException {

    public OrderStatusException(String message) {
        super(message);
    }
}
