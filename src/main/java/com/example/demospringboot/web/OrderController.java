package com.example.demospringboot.web;

import com.example.demospringboot.domain.Order;
import com.example.demospringboot.dto.OrderReadDto;
import com.example.demospringboot.service.interfaces.OrderService;
import com.example.demospringboot.util.mapper.OrderMapper;
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
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper mapper;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/order")
    public OrderReadDto createOrder() {
        Order order = orderService.create();
        return mapper.toReadDto(order);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/order/com")
    public OrderReadDto completeOrder(@RequestHeader Integer orderId) {
        Order order = orderService.completeOrder(orderId);
        return mapper.toReadDto(order);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/order/dec")
    public OrderReadDto declineOrder(@RequestHeader Integer orderId) {
        Order order = orderService.declineOrder(orderId);
        return mapper.toReadDto(order);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/order")
    public List<OrderReadDto> getAllOrders() {
        List<Order> orders = orderService.getAll();
        return mapper.toReadDtoCol(orders);
    }

}
