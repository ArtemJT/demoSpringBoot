package com.example.demospringboot.dto;

import com.example.demospringboot.domain.Customer;
import com.example.demospringboot.domain.Manager;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

/**
 * @author Artem Kovalov on 11.08.2023
 */
@Builder
public record OrderDto(
        @Positive
        Integer id,

        @NotNull
        @NotEmpty
        Customer customer,

        @NotNull
        @NotEmpty
        Manager manager) {

}
