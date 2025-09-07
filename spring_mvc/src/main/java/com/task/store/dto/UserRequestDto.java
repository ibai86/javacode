package com.task.store.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record UserRequestDto(
        Long id,

        @NotEmpty
        String username,

        @NotEmpty
        @Email
        String email
) {
}
