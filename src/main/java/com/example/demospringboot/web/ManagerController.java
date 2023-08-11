package com.example.demospringboot.web;

import com.example.demospringboot.domain.Manager;
import com.example.demospringboot.dto.ManagerDto;
import com.example.demospringboot.dto.ManagerReadDto;
import com.example.demospringboot.dto.OrderReadDto;
import com.example.demospringboot.service.interfaces.ManagerService;
import com.example.demospringboot.util.mapper.ManagerMapper;
import com.example.demospringboot.util.mapper.OrderMapper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Artem Kovalov on 11.08.2023
 */
@RestController
@AllArgsConstructor
@RequestMapping(value = "/app", produces = MediaType.APPLICATION_JSON_VALUE)
public class ManagerController {

    private final ManagerService managerService;
    private final ManagerMapper managerMapper;
    private final OrderMapper orderMapper;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/manager")
    public ManagerReadDto createManager(@RequestBody @Valid ManagerDto managerDto) {
        Manager manager = managerMapper.toEntity(managerDto);
        return managerMapper.toReadDto(managerService.create(manager));
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/manager")
    public ManagerReadDto deleteManager(@RequestParam Integer id) {
        return managerMapper.toReadDto(managerService.deleteById(id));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/manager")
    public ManagerReadDto getManagerById(@RequestParam Integer id) {
        return managerMapper.toReadDto(managerService.getById(id));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/manager/all")
    public List<ManagerReadDto> getAllManagers() {
        return managerMapper.toReadDtoCol(managerService.getAll());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/manager/orders")
    public List<OrderReadDto> getAllManagerOrders(@RequestParam Integer id) {
        return orderMapper.toReadDtoCol(managerService.getAllOrdersByManagerId(id));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/manager/orders/process")
    public List<OrderReadDto> getAllManagerProcessingOrders(@RequestParam Integer id) {
        return orderMapper.toReadDtoCol(managerService.getAllProcessingOrdersForManager(id));
    }
}
