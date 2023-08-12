package com.example.demospringboot.service.interfaces;

import com.example.demospringboot.domain.Booking;
import com.example.demospringboot.domain.Customer;

import java.util.List;

/**
 * @author Artem Kovalov on 10.08.2023
 */
public interface BookingService {

    Booking create(Customer customer);

    Booking getById(Integer id);

    List<Booking> getAll();

    List<Booking> getAllNotAssign();

    Booking completeOrder(Integer id);

    Booking declineOrder(Integer id);

    Booking assignManagerToBooking(Integer bookingId, Integer managerId);
}
