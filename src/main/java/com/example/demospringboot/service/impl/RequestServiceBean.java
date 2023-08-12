package com.example.demospringboot.service.impl;

import com.example.demospringboot.domain.Request;
import com.example.demospringboot.domain.RequestStatus;
import com.example.demospringboot.domain.Customer;
import com.example.demospringboot.domain.Manager;
import com.example.demospringboot.repository.RequestRepository;
import com.example.demospringboot.service.interfaces.CustomerService;
import com.example.demospringboot.service.interfaces.ManagerService;
import com.example.demospringboot.service.interfaces.RequestService;
import com.example.demospringboot.util.exception.RequestAssignException;
import com.example.demospringboot.util.exception.RequestNotAssignException;
import com.example.demospringboot.util.exception.RequestStatusException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demospringboot.domain.RequestStatus.*;

/**
 * @author Artem Kovalov on 10.08.2023
 */
@AllArgsConstructor
@Service
public class RequestServiceBean implements RequestService {

    private final RequestRepository requestRepository;
    private final ManagerService managerService;
    private final CustomerService customerService;

    @Override
    public Request create(Customer customer) {
        // TODO Delete this. It's temporary for testing.
        customer = customerService.getRandomCustomer();

        Request request = Request.builder()
                .customer(customer)
                .status(RequestStatus.IN_PROGRESS)
                .build();
        return requestRepository.save(request);
    }

    @Override
    public Request getById(Integer id) throws EntityNotFoundException {
        return requestRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Request> getAll() {
        return requestRepository.findAll();
    }

    @Override
    public List<Request> getAllNotAssign() {
        return getAll().stream()
                .filter(b -> b.getManager() == null)
                .toList();
    }

    @Override
    public Request completeRequest(Integer id) {
        Request requestById = getById(id);
        checkAssign(requestById);

        requestById.setStatus(checkRequestStatus(requestById, COMPLETED));
        return requestRepository.save(requestById);
    }

    @Override
    public Request declineRequest(Integer id) {
        Request requestById = getById(id);
        checkAssign(requestById);

        requestById.setStatus(checkRequestStatus(requestById, DECLINED));
        return requestRepository.save(requestById);
    }

    @Override
    public Request assignManagerToRequest(Integer bookingId, Integer managerId) {
        Request request = getById(bookingId);
        if (request.getManager() != null) {
            throw new RequestAssignException(String.valueOf(bookingId));
        }

        Manager manager = managerService.getById(managerId);
        request.setManager(manager);
        return requestRepository.save(request);
    }

    private RequestStatus checkRequestStatus(Request request, RequestStatus status) {
        if (status.equals(request.getStatus())) {
            throw new RequestStatusException(status.name());
        }
        return status;
    }

    private void checkAssign(Request requestById) {
        if (requestById.getManager() == null) {
            throw new RequestNotAssignException(String.valueOf(requestById.getId()));
        }
    }
}