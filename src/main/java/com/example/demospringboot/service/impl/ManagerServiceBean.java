package com.example.demospringboot.service.impl;

import com.example.demospringboot.domain.Booking;
import com.example.demospringboot.domain.BookingStatus;
import com.example.demospringboot.domain.Manager;
import com.example.demospringboot.repository.BookingRepository;
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
    private final BookingRepository bookingRepository;

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
    public List<Booking> getAllOrdersByManagerId(Integer id) throws EntityNotFoundException {
        return bookingRepository.findAllByManager(getById(id));
    }

    @Override
    public List<Booking> getAllProcessingOrdersForManager(Integer id) {
        return getAllOrdersByManagerId(id).stream()
                .filter(order -> BookingStatus.PROCESSING.equals(order.getStatus()))
                .toList();
    }
}
