package de.workshops.bookshelf.book;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BookService {

    private final BookJdbcRepository repository;


    public BookService(BookJdbcRepository repository) {
        this.repository = repository;
    }

    List<Book> getAllBooks() {
        return repository.getAllBooks();
    }

    Book getByIsbn(String isbn) throws BookNotFoundException {
        return repository.getAllBooks().stream().filter(book -> book.getIsbn().equals(isbn)).findFirst().orElseThrow(BookNotFoundException::new);
    }

    List<Book> getByAuthor(String author) {
        return repository.getAllBooks().stream().filter(book -> book.getAuthor().startsWith(author)).toList();
    }

    List<Book> searchBooks(BookSearch bookSearch) {
        return repository.getAllBooks().stream().filter(
                book -> book.getAuthor().startsWith(bookSearch.getAuthorName())
                        || book.getIsbn().equals(bookSearch.getIsbn())).toList();
    }

    void addBook (Book book) {
        repository.createBook(book);
    }
}
