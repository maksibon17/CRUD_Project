package com.CRUD_Project.repositories;

import com.CRUD_Project.entities.Author;
import com.CRUD_Project.entities.Book;
import com.CRUD_Project.entities.GenreBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book,Integer> {
    Optional<Book> findByTitleAndAuthorAndGenreBook(String title, Author author, GenreBook genreBook);
}
