package com.CRUD_Project.repositories;

import com.CRUD_Project.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Integer> {
}
