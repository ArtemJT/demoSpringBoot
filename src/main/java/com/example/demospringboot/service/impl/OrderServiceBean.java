package com.example.demospringboot.service.impl;

import com.example.demospringboot.domain.Order;
import com.example.demospringboot.domain.OrderStatus;
import com.example.demospringboot.repository.OrderRepository;
import com.example.demospringboot.service.interfaces.CustomerService;
import com.example.demospringboot.service.interfaces.ManagerService;
import com.example.demospringboot.service.interfaces.OrderService;
import com.example.demospringboot.util.exception.OrderStatusException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demospringboot.domain.OrderStatus.*;

/**
 * @author Artem Kovalov on 10.08.2023
 */
@AllArgsConstructor
@Service
public class OrderServiceBean implements OrderService {

    private final OrderRepository orderRepository;
    private final ManagerService managerService;
    private final CustomerService customerService;

    @Override
    public Order create() {
        Order order = Order.builder()
                .customer(customerService.getRandomCustomer())
                .manager(managerService.getRandomManager())
                .status(OrderStatus.PROCESSING)
                .build();
        return orderRepository.save(order);
    }

    @Override
    public Order getById(Integer id) throws EntityNotFoundException {
        return orderRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order completeOrder(Integer id) {
        Order orderById = getById(id);
        if (COMPLETED.equals(orderById.getStatus())) {
            throw new OrderStatusException(COMPLETED.name());
        }

        orderById.setStatus(COMPLETED);
        return orderRepository.save(orderById);
    }

    @Override
    public Order declineOrder(Integer id) {
        Order orderById = getById(id);
        if (DECLINED.equals(orderById.getStatus())) {
            throw new OrderStatusException(DECLINED.name());
        }

        orderById.setStatus(DECLINED);
        return orderRepository.save(orderById);
    }
}
