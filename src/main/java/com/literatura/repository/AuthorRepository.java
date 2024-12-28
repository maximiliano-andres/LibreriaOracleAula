package com.literatura.repository;

import com.literatura.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Author> findByNameContaining(String name);
    List<Author> findByBirthYear(int year);
    List<Author> findByDeathYear(int year);

    // Método en AuthorRepository
    Author findByName(String name);

}
