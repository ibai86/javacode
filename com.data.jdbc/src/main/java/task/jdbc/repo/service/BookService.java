package task.jdbc.repo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import task.jdbc.repo.model.Book;
import task.jdbc.repo.repository.BookJdbcRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookJdbcRepository repository;

    @Transactional
    public Book createBook(Book book) {
        return repository.save(book);
    }

    @Transactional
    public Book updateBook(Book book) {
        Book bookToUpdate = checkBookExistAndReturn(book.getId());

        bookToUpdate.setTitle(book.getTitle());
        bookToUpdate.setAuthor(book.getAuthor());
        bookToUpdate.setPublicationYear(book.getPublicationYear());

        return repository.update(bookToUpdate);
    }

    @Transactional(readOnly = true)
    public Book getBook(Long id) {
        return checkBookExistAndReturn(id);
    }

    @Transactional(readOnly = true)
    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    @Transactional
    public void deleteBook(Long id) {
        checkBookExistAndReturn(id);
        repository.delete(id);
    }

    private Book checkBookExistAndReturn(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Book not found"));
    }
}
