package com.task.library.controller;

import com.task.library.dto.BookDto;
import com.task.library.mapper.BookMapper;
import com.task.library.model.Book;
import com.task.library.service.LibraryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/books")
@Validated
@RequiredArgsConstructor
public class LibraryController {
    private final LibraryService libraryService;
    private final BookMapper bookMapper;

    @PostMapping
    public ResponseEntity<BookDto> createBook(@Valid @RequestBody BookDto dto) {
        Book newBook = libraryService.createBook(dto);
        return new ResponseEntity<>(bookMapper.toDto(newBook), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<BookDto> updateBook(@Valid @RequestBody BookDto dto) {
        Book updatedBook = libraryService.updateBook(dto);
        return new ResponseEntity<>(bookMapper.toDto(updatedBook), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBook(@Positive @PathVariable Long id) {
        Book book = libraryService.getBook(id);
        return new ResponseEntity<>(bookMapper.toDto(book), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@Positive @PathVariable Long id) {
        libraryService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/page")
    public ResponseEntity<Page<BookDto>> getPageOfBooks(
                                                        @PageableDefault(size = 20, sort = "title") Pageable pageable) {
        Page<Book> bookPage = libraryService.getAllBooks(pageable);
        return new ResponseEntity<>(bookPage.map(bookMapper::toDto), HttpStatus.OK);
    }
}
