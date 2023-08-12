package com.example.demospringboot;

import com.example.demospringboot.domain.Booking;
import com.example.demospringboot.domain.BookingStatus;
import com.example.demospringboot.domain.Customer;
import com.example.demospringboot.domain.Manager;
import com.example.demospringboot.repository.BookingRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Artem Kovalov on 12.08.2023
 */
@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Booking Repository Tests")
class BookingRepositoryTests {

    @Autowired
    private BookingRepository repository;

    private static final Manager MANAGER = Manager.builder().id(1).name("Manager").build();

    @Test
    @Order(1)
    @Rollback(value = false)
    @DisplayName("Save booking")
    void saveBookingTest() {
        var booking = Booking.builder()
                .id(1)
                .creationDate(LocalDateTime.now())
                .status(BookingStatus.PROCESSING)
                .manager(MANAGER)
                .build();

        repository.save(booking);

        assertThat(booking.getId()).isNotNull();
        assertThat(booking.getId()).isPositive();
        assertThat(booking.getStatus()).isEqualTo(BookingStatus.PROCESSING);
    }

    @Test
    @Order(2)
    @DisplayName("Get booking by id")
    void getBookingByIdTest() {

        var booking = repository.findById(1).orElseThrow();

        assertThat(booking.getId()).isNotNull();
        assertThat(booking.getId()).isOdd();
        assertThat(booking.getId()).isEqualTo(1);
    }

    @Test
    @Order(3)
    @DisplayName("Get all bookings")
    void getAllBookingsTest() {

        var bookingList = repository.findAll();

        assertThat(bookingList).isNotNull().isNotEmpty();
    }

    @Test
    @Order(4)
    @DisplayName("Get all bookings by manager")
    void getAllBookingsByManagerTest() {

        var bookingList = repository.findAllByManager(MANAGER);

        assertThat(bookingList).isNotNull().isNotEmpty();
        assertThat(bookingList.get(0).getStatus()).isEqualTo(BookingStatus.PROCESSING);
    }
}
