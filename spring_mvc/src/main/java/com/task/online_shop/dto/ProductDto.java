package com.task.online_shop.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record ProductDto(
        Long id,

        @NotBlank
        String name,
        String description,
        BigDecimal price,

        @PositiveOrZero
        int quantityInStock
) {
}
