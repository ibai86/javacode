package com.task.store.dto;

public record UserSummaryDto(
        Long id,
        String username,
        String email
) {
}
