package com.example.demospringboot.service.impl;

import com.example.demospringboot.domain.Booking;
import com.example.demospringboot.domain.BookingStatus;
import com.example.demospringboot.repository.BookingRepository;
import com.example.demospringboot.service.interfaces.CustomerService;
import com.example.demospringboot.service.interfaces.ManagerService;
import com.example.demospringboot.service.interfaces.BookingService;
import com.example.demospringboot.util.exception.OrderStatusException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demospringboot.domain.BookingStatus.*;

/**
 * @author Artem Kovalov on 10.08.2023
 */
@AllArgsConstructor
@Service
public class BookingServiceBean implements BookingService {

    private final BookingRepository bookingRepository;
    private final ManagerService managerService;
    private final CustomerService customerService;

    @Override
    public Booking create() {
        Booking booking = Booking.builder()
                .customer(customerService.getRandomCustomer())
                .manager(managerService.getRandomManager())
                .status(BookingStatus.PROCESSING)
                .build();
        return bookingRepository.save(booking);
    }

    @Override
    public Booking getById(Integer id) throws EntityNotFoundException {
        return bookingRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Booking> getAll() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking completeOrder(Integer id) {
        Booking bookingById = getById(id);
        if (COMPLETED.equals(bookingById.getStatus())) {
            throw new OrderStatusException(COMPLETED.name());
        }

        bookingById.setStatus(COMPLETED);
        return bookingRepository.save(bookingById);
    }

    @Override
    public Booking declineOrder(Integer id) {
        Booking bookingById = getById(id);
        if (DECLINED.equals(bookingById.getStatus())) {
            throw new OrderStatusException(DECLINED.name());
        }

        bookingById.setStatus(DECLINED);
        return bookingRepository.save(bookingById);
    }
}
