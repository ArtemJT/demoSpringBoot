package com.example.demospringboot.service.interfaces;

import com.example.demospringboot.domain.Customer;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

/**
 * @author Artem Kovalov on 10.08.2023
 */
public interface CustomerService {

    /**
     * Saving record Customer into DB
     *
     * @param customer passed from JSON-request
     * @return object Customer if saving successful
     */
    Customer create(Customer customer);

    /**
     * Finding record by ID
     *
     * @param id Customer id
     * @return Customer which found in DB
     * @throws EntityNotFoundException if object not found
     */
    Customer getById(Integer id) throws EntityNotFoundException;

    /**
     * Finding all records
     *
     * @return list of records
     */
    List<Customer> getAll();

    /**
     * Marking record as 'deleted'
     *
     * @param id Customer id
     * @return marked record if successful
     * @throws EntityNotFoundException if object not found
     */
    Customer deleteById(Integer id) throws EntityNotFoundException;

    // TODO Delete temporary method
    Customer getRandomCustomer();
}
