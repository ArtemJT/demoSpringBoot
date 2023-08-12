package com.example.demospringboot.web;

import com.example.demospringboot.domain.Booking;
import com.example.demospringboot.dto.BookingReadDto;
import com.example.demospringboot.service.interfaces.BookingService;
import com.example.demospringboot.util.mapper.BookingMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Artem Kovalov on 10.08.2023
 */
@RestController
@AllArgsConstructor
@RequestMapping(value = "/app", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookingController {

    private final BookingService bookingService;
    private final BookingMapper mapper;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/order")
    public BookingReadDto createOrder() {
        Booking booking = bookingService.create();
        return mapper.toReadDto(booking);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/order/com")
    public BookingReadDto completeOrder(@RequestHeader Integer orderId) {
        Booking booking = bookingService.completeOrder(orderId);
        return mapper.toReadDto(booking);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/order/dec")
    public BookingReadDto declineOrder(@RequestHeader Integer orderId) {
        Booking booking = bookingService.declineOrder(orderId);
        return mapper.toReadDto(booking);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/order")
    public List<BookingReadDto> getAllOrders() {
        List<Booking> bookings = bookingService.getAll();
        return mapper.toReadDtoCol(bookings);
    }

}
