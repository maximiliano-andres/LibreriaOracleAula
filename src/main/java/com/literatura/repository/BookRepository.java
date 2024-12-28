package com.literatura.repository;

import com.literatura.model.Author;
import com.literatura.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitleContaining(String title);
    List<Book> findByLanguage(String language);

    // Método en BookRepository
    List<Book> findByTitle(String title);
    // Método en BookRepository
    List<Book> findByAuthor(Author author);

}
