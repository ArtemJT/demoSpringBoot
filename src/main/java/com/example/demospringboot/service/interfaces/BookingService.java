package com.example.demospringboot.service.interfaces;

import com.example.demospringboot.domain.Booking;

import java.util.List;

/**
 * @author Artem Kovalov on 10.08.2023
 */
public interface BookingService {

    // TODO Add Customer as parameter
    Booking create();

    Booking getById(Integer id);

    List<Booking> getAll();

    Booking completeOrder(Integer id);

    Booking declineOrder(Integer id);
}
