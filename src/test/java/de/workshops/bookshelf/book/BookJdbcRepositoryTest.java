package de.workshops.bookshelf.book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Repository;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(
        includeFilters = @ComponentScan.Filter(
                type = FilterType.ANNOTATION,
                classes = Repository.class
        )
)
class BookJdbcRepositoryTest {

    @Autowired
    BookJdbcRepository repository;

    @Test
    void testGetAllBooks() {
        assertThat(repository.getAllBooks()).hasSize(3);
    }
}