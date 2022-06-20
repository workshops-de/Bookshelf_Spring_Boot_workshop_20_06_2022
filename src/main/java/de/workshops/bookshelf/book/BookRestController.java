package de.workshops.bookshelf.book;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ResourceLoader;
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

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("book")
public class BookRestController {

    private final ObjectMapper mapper;
    private final ResourceLoader resourceLoader;

    private List<Book> books;

    public BookRestController(ObjectMapper mapper, ResourceLoader resourceLoader) {
        this.mapper = mapper;
        this.resourceLoader = resourceLoader;
    }

    @PostConstruct
    public void init() throws IOException {
        final var resource = resourceLoader.getResource("classpath:books.json");
        books = mapper.readValue(resource.getInputStream(), new TypeReference<>() {
        });
    }

    @GetMapping
    ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(books);
    }

    @GetMapping(path = "{isbn}")
    ResponseEntity<Book> getByIsbn(@PathVariable String isbn) throws BookNotFoundException {
        final var buch = books.stream().filter(book -> book.getIsbn().equals(isbn)).findFirst().orElseThrow(BookNotFoundException::new);
        return ResponseEntity.ok(buch);
    }

    @GetMapping(params = "author")
    ResponseEntity<List<Book>> getByAuthor(@RequestParam String author) throws BookNotFoundException {
        final var foundBooks = books.stream().filter(book -> book.getAuthor().startsWith(author)).toList();
        if (foundBooks.isEmpty()) {
            throw new BookNotFoundException();
        }
        return ResponseEntity.ok(foundBooks);
    }

    @PostMapping(path = "search")
    ResponseEntity<List<Book>> searchBooks(@RequestBody @Valid BookSearch bookSearch) throws BookNotFoundException {
        final var foundBooks = books.stream().filter(
                book -> book.getAuthor().startsWith(bookSearch.getAuthorName())
                || book.getIsbn().equals(bookSearch.getIsbn())).toList();
        if (foundBooks.isEmpty()) {
            throw new BookNotFoundException();
        }
        return ResponseEntity.ok(foundBooks);
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<Void> bookNotFoundExceptionHandler() {
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
    }
}
