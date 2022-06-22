package de.workshops.bookshelf.book;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceUsingJpaRepository {
    private final BookJpaRepository repository;

    public BookServiceUsingJpaRepository(BookJpaRepository repository) {
        this.repository = repository;
    }

    List<Book> getAllBooks() {
        return repository.findAll();
    }

    Book getByIsbn(String isbn) throws BookNotFoundException {
        final var book = repository.findByIsbn(isbn);
        if (book == null) {
            throw new BookNotFoundException();
        }
        return book;
    }

    List<Book> getByAuthor(String author) {
        return repository.findAll().stream().filter(book -> book.getAuthor().startsWith(author)).toList();
    }

    List<Book> searchBooks(BookSearch bookSearch) {
        return repository.findAll().stream().filter(
                book -> book.getAuthor().startsWith(bookSearch.getAuthorName())
                        || book.getIsbn().equals(bookSearch.getIsbn())).toList();
    }

    void addBook (Book book) {
        repository.save(book);
    }
}
