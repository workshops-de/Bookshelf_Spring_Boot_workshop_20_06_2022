package de.workshops.bookshelf.book;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookJdbcRepository {
    private final JdbcTemplate template;

    public BookJdbcRepository(JdbcTemplate template) {
        this.template = template;
    }

    List<Book> getAllBooks() {
        return template.query("SELECT * FROM book", new BeanPropertyRowMapper<>(Book.class));
    }

    void createBook(Book book) {
        String sql = "INSERT INTO book (id, title, description, author, isbn) VALUES (?, ?, ?, ?, ?)";
        template.update(sql, book.getId(), book.getTitle(), book.getDescription(), book.getAuthor(), book.getIsbn());
    }
}
