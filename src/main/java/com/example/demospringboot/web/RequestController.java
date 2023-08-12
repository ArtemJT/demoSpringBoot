package com.example.demospringboot.web;

import com.example.demospringboot.domain.Request;
import com.example.demospringboot.domain.Customer;
import com.example.demospringboot.dto.RequestFullDto;
import com.example.demospringboot.dto.RequestReadDto;
import com.example.demospringboot.service.interfaces.RequestService;
import com.example.demospringboot.util.mapper.RequestMapper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Artem Kovalov on 10.08.2023
 */
@RestController
@AllArgsConstructor
@RequestMapping(value = "/app", produces = MediaType.APPLICATION_JSON_VALUE)
public class RequestController {

    private final RequestService requestService;
    private final RequestMapper requestMapper;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/request")
    public RequestReadDto createRequest(@RequestBody @Valid Customer customer) {
        Request request = requestService.create(customer);
        return requestMapper.toReadDto(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("request/assign")
    public RequestFullDto assignManager(@RequestParam Integer orderId, @RequestParam Integer managerId) {
        Request request = requestService.assignManagerToRequest(orderId, managerId);
        return requestMapper.toFullDto(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/request/complete")
    public RequestReadDto completeRequest(@RequestHeader Integer orderId) {
        Request request = requestService.completeRequest(orderId);
        return requestMapper.toReadDto(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/request/decline")
    public RequestReadDto declineRequest(@RequestHeader Integer orderId) {
        Request request = requestService.declineRequest(orderId);
        return requestMapper.toReadDto(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/request/all")
    public List<RequestReadDto> getAllRequests() {
        List<Request> requests = requestService.getAll();
        return requestMapper.toReadDtoCol(requests);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/request/not_assign")
    public List<RequestReadDto> getAllNotAssignRequests() {
        List<Request> requests = requestService.getAllNotAssign();
        return requestMapper.toReadDtoCol(requests);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/request/{id}")
    public RequestReadDto getRequestById(@PathVariable Integer id) {
        Request request = requestService.getById(id);
        return requestMapper.toReadDto(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/request/manager")
    public List<RequestReadDto> getAllManagerRequests(@RequestParam Integer id) {
        return requestMapper.toReadDtoCol(requestService.getAllRequestsByManagerId(id));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/request/manager/in_progress")
    public List<RequestReadDto> getAllManagerProcessingRequests(@RequestParam Integer id) {
        return requestMapper.toReadDtoCol(requestService.getAllInProgressRequestsForManager(id));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/request/customer")
    public List<RequestReadDto> getAllCustomerRequests(@RequestParam Integer id) {
        return requestMapper.toReadDtoCol(requestService.getAllRequestsByCustomerId(id));
    }

}
