package com.example.demospringboot.web;

import com.example.demospringboot.domain.Customer;
import com.example.demospringboot.dto.CustomerDto;
import com.example.demospringboot.dto.BookingReadDto;
import com.example.demospringboot.service.interfaces.CustomerService;
import com.example.demospringboot.util.mapper.CustomerMapper;
import com.example.demospringboot.util.mapper.BookingMapper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Artem Kovalov on 11.08.2023
 */
@RestController
@AllArgsConstructor
@RequestMapping(value = "/app", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;
    private final BookingMapper bookingMapper;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/customer")
    public CustomerDto createCustomer(@RequestBody @Valid CustomerDto customerDto) {
        Customer customer = customerMapper.toEntity(customerDto);
        return customerMapper.toDto(customerService.create(customer));
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/customer")
    public CustomerDto deleteCustomer(@RequestParam Integer id) {
        return customerMapper.toDto(customerService.deleteById(id));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/customer")
    public CustomerDto getCustomerById(@RequestParam Integer id) {
        return customerMapper.toDto(customerService.getById(id));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/customer/all")
    public List<CustomerDto> getAllCustomers() {
        return customerMapper.toDtoCol(customerService.getAll());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/customer/orders")
    public List<BookingReadDto> getAllCustomerOrders(@RequestParam Integer id) {
        return bookingMapper.toReadDtoCol(customerService.getAllOrdersByCustomerId(id));
    }
}
