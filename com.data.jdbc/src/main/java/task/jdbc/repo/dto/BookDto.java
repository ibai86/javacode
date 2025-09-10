package task.jdbc.repo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import task.jdbc.repo.model.Book;

public record BookDto(
        Long id,

        @NotBlank
        String title,

        @NotBlank
        String author,

        @Positive
        int publicationYear
) {
    public static BookDto toDto(Book book) {
        return new BookDto(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getPublicationYear()
        );
    }

    public static Book toEntity(BookDto dto) {
        return new Book(
                dto.id(),
                dto.title(),
                dto.author(),
                dto.publicationYear()
        );
    }
}
