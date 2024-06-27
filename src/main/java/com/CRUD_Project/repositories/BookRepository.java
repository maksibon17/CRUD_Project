package com.CRUD_Project.repository;

import com.CRUD_Project.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Integer> {
}
