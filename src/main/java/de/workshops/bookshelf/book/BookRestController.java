package de.workshops.bookshelf.book;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("book")
public class BookRestController {
    private final BookService service;
//    private final BookServiceUsingJpaRepository service;

//    public BookRestController(BookServiceUsingJpaRepository service) {
//        this.service = service;
//    }
    public BookRestController(BookService service) {
        this.service = service;
    }

    @GetMapping
    ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(service.getAllBooks());
    }

    @GetMapping(path = "{isbn}")
    ResponseEntity<Book> getByIsbn(@PathVariable String isbn) throws BookNotFoundException {
        final var buch = service.getByIsbn(isbn);
        return ResponseEntity.ok(buch);
    }

    @GetMapping(params = "author")
    ResponseEntity<List<Book>> getByAuthor(@RequestParam String author) throws BookNotFoundException {
        final var foundBooks = service.getByAuthor(author);
        if (foundBooks.isEmpty()) {
            throw new BookNotFoundException();
        }
        return ResponseEntity.ok(foundBooks);
    }

    @PostMapping(path = "search")
    ResponseEntity<List<Book>> searchBooks(@RequestBody @Valid BookSearch bookSearch) throws BookNotFoundException {
        final var foundBooks = service.searchBooks(bookSearch);
        if (foundBooks.isEmpty()) {
            throw new BookNotFoundException();
        }
        return ResponseEntity.ok(foundBooks);
    }

    @PostMapping
    ResponseEntity<Void> addBook(@RequestBody Book book) {
        service.addBook(book);
        return ResponseEntity.created(URI.create("/book/" + book.getIsbn())).build();
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<Void> bookNotFoundExceptionHandler() {
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
    }
}
