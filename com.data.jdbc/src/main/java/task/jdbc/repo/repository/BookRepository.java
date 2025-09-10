package task.jdbc.repo.repository;

import org.springframework.dao.EmptyResultDataAccessException;
import task.jdbc.repo.model.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class BookRepository implements BookJdbcRepository {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert insert;

    public BookRepository(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.insert = new SimpleJdbcInsert(dataSource)
                .withTableName("books")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Book save(Book book) {
        Map<String, Object> params = new HashMap<>();

        params.put("title", book.getTitle());
        params.put("author", book.getAuthor());
        params.put("publication_year", book.getPublicationYear());

        Number newId = insert.executeAndReturnKey(params);
        book.setId(newId.longValue());

        return book;
    }

    @Override
    public Book update(Book book) {
        String sql = "UPDATE books SET title = ?, author = ?, publication_year = ? WHERE id = ?";
        int rowAffected = jdbcTemplate.update(
                sql, book.getTitle(), book.getAuthor(), book.getPublicationYear(), book.getId());

        if (rowAffected == 0) {
            throw new EmptyResultDataAccessException("Book not found: " + book.getId(), 1);
        }

        return book;
    }

    @Override
    public Optional<Book> findById(Long id) {
        String sql = "SELECT id, title, author, publication_year FROM books WHERE id = ?";
        Book foundBook = jdbcTemplate.queryForObject(sql, getRowMapper(), id);
        return Optional.ofNullable(foundBook);
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM books WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Book> findAll() {
        String sql = "SELECT id, title, author, publication_year FROM books ORDER BY title";
        return jdbcTemplate.query(sql, getRowMapper());
    }
}
