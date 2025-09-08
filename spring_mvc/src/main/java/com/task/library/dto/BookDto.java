package com.task.library.dto;

import com.task.library.model.Book;

import java.time.LocalDate;

public record BookDto(
        Long id,
        String title,
        String isbn,
        Book.Genre genre,
        Integer pageCount,
        LocalDate publicationDate,
        AuthorDto author
) {
}
