package com.task.library.dto;

import java.time.LocalDate;

public record AuthorDto(
        Long id,
        String name,
        String email,
        LocalDate dateOfBirth
) {
}
