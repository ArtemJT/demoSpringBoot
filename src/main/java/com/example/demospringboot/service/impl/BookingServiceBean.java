package com.example.demospringboot.service.impl;

import com.example.demospringboot.domain.Booking;
import com.example.demospringboot.domain.BookingStatus;
import com.example.demospringboot.domain.Customer;
import com.example.demospringboot.domain.Manager;
import com.example.demospringboot.repository.BookingRepository;
import com.example.demospringboot.service.interfaces.CustomerService;
import com.example.demospringboot.service.interfaces.ManagerService;
import com.example.demospringboot.service.interfaces.BookingService;
import com.example.demospringboot.util.exception.BookingAssignException;
import com.example.demospringboot.util.exception.BookingNotAssignException;
import com.example.demospringboot.util.exception.BookingStatusException;
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
    public Booking create(Customer customer) {
        // TODO Delete this. It's temporary for testing.
        customer = customerService.getRandomCustomer();

        Booking booking = Booking.builder()
                .customer(customer)
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
        checkAssign(bookingById);

        bookingById.setStatus(validateBookingStatus(bookingById, COMPLETED));
        return bookingRepository.save(bookingById);
    }

    @Override
    public Booking declineOrder(Integer id) {
        Booking bookingById = getById(id);
        checkAssign(bookingById);

        bookingById.setStatus(validateBookingStatus(bookingById, DECLINED));
        return bookingRepository.save(bookingById);
    }

    @Override
    public List<Booking> getAllNotAssign() {
        return getAll().stream()
                .filter(b -> b.getManager() == null)
                .toList();
    }

    @Override
    public Booking assignManagerToBooking(Integer bookingId, Integer managerId) {
        Booking booking = getById(bookingId);
        if (booking.getManager() != null) {
            throw new BookingAssignException(String.valueOf(bookingId));
        }

        Manager manager = managerService.getById(managerId);
        booking.setManager(manager);
        return bookingRepository.save(booking);
    }

    private BookingStatus validateBookingStatus(Booking booking, BookingStatus status) {
        if (status.equals(booking.getStatus())) {
            throw new BookingStatusException(status.name());
        }
        return status;
    }

    private void checkAssign(Booking bookingById) {
        if (bookingById.getManager() == null) {
            throw new BookingNotAssignException(String.valueOf(bookingById.getId()));
        }
    }
}
