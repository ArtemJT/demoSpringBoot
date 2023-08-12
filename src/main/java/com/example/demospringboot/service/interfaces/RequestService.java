package com.example.demospringboot.service.interfaces;

import com.example.demospringboot.domain.Request;
import com.example.demospringboot.domain.Customer;
import jakarta.persistence.EntityNotFoundException;

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

    /**
     * Finding all records by Customer ID
     * @param id Customer id
     * @return list of Requests which belong to this Customer
     * @throws EntityNotFoundException if object not found
     */
    List<Request> getAllRequestsByCustomerId(Integer id) throws EntityNotFoundException;

    /**
     * Finding all records by Manager ID
     * @param id Manager id
     * @return list of Requests which belong to this Manager
     * @throws EntityNotFoundException if object not found
     */
    List<Request> getAllRequestsByManagerId(Integer id) throws EntityNotFoundException;

    /**
     * Finding all records that has status 'IN PROGRESS' by Manager ID
     * @param id Manager id
     * @return list of Requests with status 'IN PROGRESS' which belong to this Manager
     * @throws EntityNotFoundException if object not found
     */
    List<Request> getAllInProgressRequestsForManager(Integer id) throws EntityNotFoundException;
}
