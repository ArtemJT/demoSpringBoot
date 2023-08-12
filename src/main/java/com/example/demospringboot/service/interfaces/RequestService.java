package com.example.demospringboot.service.interfaces;

import com.example.demospringboot.domain.Request;
import com.example.demospringboot.domain.Customer;

import java.util.List;

/**
 * @author Artem Kovalov on 10.08.2023
 */
public interface RequestService {

    Request create(Customer customer);

    Request getById(Integer id);

    List<Request> getAll();

    List<Request> getAllNotAssign();

    Request completeRequest(Integer id);

    Request declineRequest(Integer id);

    Request assignManagerToRequest(Integer bookingId, Integer managerId);
}
