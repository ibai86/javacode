package store.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CustomerDto(
        Long id,

        @NotBlank
        String firstName,

        @NotBlank
        String lastName,

        @Email
        String email,

        @NotBlank
        String contactNumber
) {
}
