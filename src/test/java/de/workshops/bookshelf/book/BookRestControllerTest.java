package de.workshops.bookshelf.book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BookRestControllerTest {

    @Autowired
    private BookRestController bookRestController;

    @Test
    void testGettingAllBooks() {
        assertThat(bookRestController.getAllBooks().getBody()).hasSize(3);
    }
}