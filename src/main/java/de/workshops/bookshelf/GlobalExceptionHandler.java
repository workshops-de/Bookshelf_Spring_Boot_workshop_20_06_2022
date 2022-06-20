package de.workshops.bookshelf;

import de.workshops.bookshelf.book.BookNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<Void> bookNotFoundExceptionHandler() {
        return ResponseEntity.notFound().build();
    }
}
