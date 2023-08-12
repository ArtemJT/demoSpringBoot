package com.example.demospringboot.service.interfaces;

import com.example.demospringboot.domain.Customer;
import com.example.demospringboot.domain.Request;

import java.util.List;

/**
 * @author Artem Kovalov on 10.08.2023
 */
public interface CustomerService {

    Customer create(Customer customer);

    Customer getById(Integer id);

    List<Customer> getAll();

    Customer deleteById(Integer id);

    // TODO Delete temporary method
    Customer getRandomCustomer();

    List<Request> getAllRequestsByCustomerId(Integer id);
}
