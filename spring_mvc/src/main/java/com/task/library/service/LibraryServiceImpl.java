package com.task.library.service;

import com.task.library.dto.BookDto;
import com.task.library.model.Book;
import org.springframework.data.domain.Page;

import java.util.List;

public class LibraryServiceImpl implements LibraryService {

    @Override
    public Book createBook(BookDto bookDto) {
        return null;
    }

    @Override
    public Book updateBook(BookDto bookDto) {
        return null;
    }

    @Override
    public void deleteBook(Long id) {

    }

    @Override
    public Book getBook(Long id) {
        return null;
    }

    @Override
    public Page<Book> getAllBooks(int page, int size, String sortedOf) {
        return null;
    }
}
