package task.jdbc.repo.repository;

import task.jdbc.repo.model.Book;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Optional;

public interface BookJdbcRepository {

    Book save(Book book);

    Book update(Book book);

    Optional<Book> findById(Long id);

    void delete(Long id);

    List<Book> findAll();

    default RowMapper<Book> getRowMapper() {
        return (resultSet, rowNum) -> Book.builder()
                .id(resultSet.getLong("id"))
                .title(resultSet.getString("title"))
                .author(resultSet.getString("author"))
                .publicationYear(resultSet.getInt("publication_year"))
                .build();
    }
}
