package com.example.demospringboot.service.interfaces;

import com.example.demospringboot.domain.Manager;
import com.example.demospringboot.domain.Booking;

import java.util.List;

/**
 * @author Artem Kovalov on 10.08.2023
 */
public interface ManagerService {

    Manager create(Manager manager);

    Manager getById(Integer id);

    List<Manager> getAll();

    Manager deleteById(Integer id);

    Manager getRandomManager();

    List<Booking> getAllOrdersByManagerId(Integer id);

    List<Booking> getAllProcessingOrdersForManager(Integer id);
}
