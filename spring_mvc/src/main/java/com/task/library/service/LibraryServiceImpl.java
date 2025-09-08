package com.task.library.service;

import com.task.library.dto.BookDto;
import com.task.library.model.Author;
import com.task.library.model.Book;
import com.task.library.repository.AuthorRepository;
import com.task.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public Book createBook(Book newBook) {
        return bookRepository.save(newBook);
    }

    @Override
    @Transactional
    public Book updateBook(BookDto bookDto) {
        if (bookDto.id() == null) {
            throw new IllegalArgumentException("Book ID must not be null for update operation.");
        }

        Book bookToUpdate = bookRepository.findById(bookDto.id())
                .orElseThrow(() -> new NoSuchElementException("Book not found"));

        bookToUpdate.setTitle(bookDto.title());
        bookToUpdate.setIsbn(bookDto.isbn());
        bookToUpdate.setGenre(bookDto.genre());
        bookToUpdate.setPageCount(bookDto.pageCount());
        bookToUpdate.setPublicationDate(bookDto.publicationDate());

        Author author = authorRepository.findById(bookDto.author().id())
                .orElseThrow(() -> new NoSuchElementException("Author not found"));
        bookToUpdate.setAuthor(author);

        return bookRepository.save(bookToUpdate);
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
    public Page<Book> getAllBooks(int page, int size, String sortedBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortedBy));
        return bookRepository.findAll(pageable);
    }
}
