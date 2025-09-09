package com.task.library.service;

import com.task.library.dto.BookDto;
import com.task.library.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LibraryService {

    Book createBook(BookDto bookDto);

    Book updateBook(BookDto bookDto);

    void deleteBook(Long id);

    Book getBook(Long id);

    Page<Book> getAllBooks(Pageable pageable);
}
