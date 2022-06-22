package de.workshops.bookshelf.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookJpaRepository extends JpaRepository<Book, Long> {
    Book findByIsbn(String isbn);
}
