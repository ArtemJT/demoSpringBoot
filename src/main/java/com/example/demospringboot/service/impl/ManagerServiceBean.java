package com.example.demospringboot.service.impl;

import com.example.demospringboot.domain.Request;
import com.example.demospringboot.domain.RequestStatus;
import com.example.demospringboot.domain.Manager;
import com.example.demospringboot.repository.RequestRepository;
import com.example.demospringboot.repository.ManagerRepository;
import com.example.demospringboot.service.interfaces.ManagerService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Artem Kovalov on 10.08.2023
 */
@AllArgsConstructor
@Service
public class ManagerServiceBean implements ManagerService {

    private final ManagerRepository managerRepository;
    private final RequestRepository requestRepository;

    @Override
    public Manager create(Manager manager) {
        return managerRepository.save(manager);
    }

    @Override
    public Manager getById(Integer id) throws EntityNotFoundException {
        return managerRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Manager> getAll() {
        return managerRepository.findAll();
    }

    @Override
    public Manager deleteById(Integer id) {
        Manager managerById = getById(id);
        managerById.setIsDeleted(true);
        return managerRepository.save(managerById);
    }

    @Override
    public List<Request> getAllRequestsByManagerId(Integer id) throws EntityNotFoundException {
        return requestRepository.findAllByManager(getById(id));
    }

    @Override
    public List<Request> getAllProcessingRequestsForManager(Integer id) {
        return getAllRequestsByManagerId(id).stream()
                .filter(order -> RequestStatus.PROCESSING.equals(order.getStatus()))
                .toList();
    }
}
