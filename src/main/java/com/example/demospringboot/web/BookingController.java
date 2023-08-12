package com.example.demospringboot.web;

import com.example.demospringboot.domain.Booking;
import com.example.demospringboot.domain.Customer;
import com.example.demospringboot.dto.BookingFullDto;
import com.example.demospringboot.dto.BookingReadDto;
import com.example.demospringboot.service.interfaces.BookingService;
import com.example.demospringboot.util.mapper.BookingMapper;
import jakarta.validation.Valid;
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
    public BookingReadDto createOrder(@RequestBody @Valid Customer customer) {
        Booking booking = bookingService.create(customer);
        return mapper.toReadDto(booking);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("order/assign")
    public BookingFullDto assignManager(@RequestParam Integer orderId, @RequestParam Integer managerId) {
        Booking booking = bookingService.assignManagerToBooking(orderId, managerId);
        return mapper.toFullDto(booking);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/order/complete")
    public BookingReadDto completeOrder(@RequestHeader Integer orderId) {
        Booking booking = bookingService.completeOrder(orderId);
        return mapper.toReadDto(booking);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/order/decline")
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

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/order/not_assign")
    public List<BookingReadDto> getAllNotAssignOrders() {
        List<Booking> bookings = bookingService.getAllNotAssign();
        return mapper.toReadDtoCol(bookings);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/order/{id}")
    public BookingReadDto getOrderById(@PathVariable Integer id) {
        Booking booking = bookingService.getById(id);
        return mapper.toReadDto(booking);
    }

}
