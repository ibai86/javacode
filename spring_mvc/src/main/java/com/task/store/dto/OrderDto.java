package com.task.store.dto;

import com.task.store.model.OrderStatus;

import java.math.BigDecimal;

public record OrderDto(
        Long id,
        OrderStatus status,
        BigDecimal totalAmount
) {
}
