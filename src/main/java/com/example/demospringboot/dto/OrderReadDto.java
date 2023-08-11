package com.example.demospringboot.dto;

import com.example.demospringboot.domain.OrderStatus;
import lombok.Builder;

import java.time.LocalDateTime;

/**
 * @author Artem Kovalov on 11.08.2023
 */
@Builder
public record OrderReadDto(Integer id, LocalDateTime creationDate, OrderStatus status) {

}
