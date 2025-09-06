package com.task.store.dto;

import com.task.store.model.Order;

import java.time.LocalDateTime;
import java.util.List;

public record UserDetailsDto(
        Long id,
        String username,
        String email,
        List<Order> orders,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
