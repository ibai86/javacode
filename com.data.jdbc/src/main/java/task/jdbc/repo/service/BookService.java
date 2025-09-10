package task.jdbc.repo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import task.jdbc.repo.model.Book;
import task.jdbc.repo.repository.BookJdbcRepository;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookJdbcRepository repository;

    public Book createBook(Book book) {
        return repository.save(book);
    }
}
