package com.task.library.dto;

import com.task.library.model.Book;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record BookDto(
        Long id,

        @NotBlank
        String title,

        @NotEmpty
        @Size(max = 20)
        String isbn,

        Book.Genre genre,

        @Positive
        Integer pageCount,

        @PastOrPresent
        LocalDate publicationDate,

        @NotNull
        AuthorDto author
) {
}
