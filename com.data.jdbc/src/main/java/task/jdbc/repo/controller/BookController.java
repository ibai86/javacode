package task.jdbc.repo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import task.jdbc.repo.dto.BookDto;
import task.jdbc.repo.model.Book;
import task.jdbc.repo.service.BookService;

@RestController
@RequestMapping("api/v1/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService service;

    @PostMapping
    public ResponseEntity<BookDto> createBook(@Valid @RequestBody BookDto dto) {
        Book newBook = service.createBook(BookDto.toEntity(dto));
        return new ResponseEntity<>(BookDto.toDto(newBook), HttpStatus.CREATED);
    }
}
