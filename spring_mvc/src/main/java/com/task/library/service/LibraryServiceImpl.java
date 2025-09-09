package com.task.library.service;

import com.task.library.dto.BookDto;
import com.task.library.model.Author;
import com.task.library.model.Book;
import com.task.library.repository.AuthorRepository;
import com.task.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class LibraryServiceImpl implements LibraryService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Override
    @Transactional
    public Book createBook(BookDto dto) {
        return bookRepository.save(prepareBookToSave(dto));
    }

    @Override
    @Transactional
    public Book updateBook(BookDto bookDto) {
        if (bookDto.id() == null) {
            throw new IllegalArgumentException("Book ID must not be null for update operation.");
        }

        Book bookToUpdate = bookRepository.findById(bookDto.id())
                .orElseThrow(() -> new NoSuchElementException("Book not found"));

        return bookRepository.save(prepareBookToSave(bookDto));
    }

    @Override
    @Transactional
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new NoSuchElementException("Book not found");
        }

        bookRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Book getBook(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Book not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Book> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    private Book prepareBookToSave(BookDto dto) {
        if (dto.author().id() == null) {
            throw new NoSuchElementException("Cannot get author id");
        }

        Author author = authorRepository.findById(dto.author().id())
                .orElseThrow(() -> new NoSuchElementException("Author not found"));

        return Book.builder()
                .title(dto.title())
                .isbn(dto.isbn())
                .genre(dto.genre())
                .pageCount(dto.pageCount())
                .publicationDate(dto.publicationDate())
                .author(author)
                .build();
    }
}
