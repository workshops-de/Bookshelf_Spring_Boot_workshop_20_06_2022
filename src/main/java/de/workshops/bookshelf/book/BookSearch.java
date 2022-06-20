package de.workshops.bookshelf.book;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class BookSearch {
    @NotEmpty(message = "darf nicht leer sein")
    private String authorName;
    private String isbn;
}
