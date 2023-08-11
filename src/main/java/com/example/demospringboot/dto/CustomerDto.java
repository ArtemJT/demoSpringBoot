package com.example.demospringboot.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

/**
 * @author Artem Kovalov on 11.08.2023
 */
@Builder
public record CustomerDto(
        @Positive
        Integer id,

        @NotNull
        @NotEmpty
        String login,

        @NotNull
        @NotEmpty
        String name,

        @NotNull
        @NotEmpty
        String email) {

}
