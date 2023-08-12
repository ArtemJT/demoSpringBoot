package com.example.demospringboot.dto;

import com.example.demospringboot.domain.BookingStatus;
import lombok.Builder;

import java.time.LocalDateTime;

/**
 * @author Artem Kovalov on 11.08.2023
 */
@Builder
public record BookingFullDto(Integer id, LocalDateTime creationDate, BookingStatus status, CustomerDto customer, ManagerDto manager) {

}
