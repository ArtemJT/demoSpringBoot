package com.example.demospringboot.dto;

import com.example.demospringboot.domain.RequestStatus;
import lombok.Builder;

import java.time.LocalDateTime;

/**
 * @author Artem Kovalov on 11.08.2023
 */
@Builder
public record RequestFullDto(Integer id, LocalDateTime creationDate, RequestStatus status, CustomerDto customer, ManagerDto manager) {

}
