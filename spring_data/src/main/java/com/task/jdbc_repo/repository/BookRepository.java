package com.task.jdbc_repo.repository;

import com.task.jdbc_repo.model.Book;
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
        return null;
    }

    @Override
    public Optional<Book> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Book> findAll() {
        return List.of();
    }
}
