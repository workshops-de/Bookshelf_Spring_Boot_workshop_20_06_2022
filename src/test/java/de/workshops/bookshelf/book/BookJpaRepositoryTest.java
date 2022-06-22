package de.workshops.bookshelf.book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class BookJpaRepositoryTest {
    @Autowired
    BookJpaRepository repository;

    @Test
    void testGetAllBooks() {
        assertThat(repository.findAll()).hasSize(3);
    }

    @Test
    void testFindByIsbn() {
        assertThat(repository.findByIsbn("12345")).isNull();
    }

}