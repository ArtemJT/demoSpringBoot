package com.example.demospringboot.service.impl;

import com.example.demospringboot.domain.Customer;
import com.example.demospringboot.domain.Request;
import com.example.demospringboot.repository.CustomerRepository;
import com.example.demospringboot.repository.RequestRepository;
import com.example.demospringboot.service.interfaces.CustomerService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * @author Artem Kovalov on 10.08.2023
 */
@AllArgsConstructor
@Service
public class CustomerServiceBean implements CustomerService {

    private final CustomerRepository customerRepository;
    private final RequestRepository requestRepository;
    private final Random random = new Random();

    @Override
    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer getById(Integer id) throws EntityNotFoundException {
        return customerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer deleteById(Integer id) throws EntityNotFoundException {
        Customer customerById = getById(id);
        customerById.setIsDeleted(Boolean.TRUE);
        return customerRepository.save(customerById);
    }

    @Override
    public Customer getRandomCustomer() throws EntityNotFoundException {
        List<Customer> customerList = getAll().stream()
                .filter(customer -> Boolean.FALSE.equals(customer.getIsDeleted()))
                .toList();

        if (customerList.isEmpty()) {
            throw new EntityNotFoundException();
        }

        int randomPos = random.nextInt(customerList.size());
        return customerList.get(randomPos);
    }

    @Override
    public List<Request> getAllRequestsByCustomerId(Integer id) throws EntityNotFoundException {
        return requestRepository.findAllByCustomer(getById(id));
    }
}
