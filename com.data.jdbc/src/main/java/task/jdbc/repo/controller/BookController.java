package task.jdbc.repo.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import task.jdbc.repo.dto.BookDto;
import task.jdbc.repo.model.Book;
import task.jdbc.repo.service.BookService;

import java.util.List;

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

    @PutMapping
    public ResponseEntity<BookDto> updateBook(@Valid @RequestBody BookDto dto) {
        Book updatedBook = service.updateBook(BookDto.toEntity(dto));
        return new ResponseEntity<>(BookDto.toDto(updatedBook), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBook(@Positive @PathVariable Long id) {
        Book foundBook = service.getBook(id);
        return new ResponseEntity<>(BookDto.toDto(foundBook), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks() {
        List<BookDto> foundBooks = service.getAllBooks().stream()
                .map(BookDto::toDto)
                .toList();
        return new ResponseEntity<>(foundBooks, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@Positive @PathVariable Long id) {
        service.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
