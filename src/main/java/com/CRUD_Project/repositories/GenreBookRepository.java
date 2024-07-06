package com.CRUD_Project.repositories;

import com.CRUD_Project.entities.GenreBook;
import com.CRUD_Project.entities.Reader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreBookRepository extends JpaRepository<GenreBook,Integer> {
    GenreBook findByName(String name);
}
