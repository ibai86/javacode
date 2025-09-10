package com.store.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record ProductDto(
        Long id,

        @NotBlank
        String name,
        String description,

        @Positive
        BigDecimal price,

        @PositiveOrZero
        int quantityInStock
) {
}
