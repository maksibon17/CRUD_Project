package com.CRUD_Project.repository;

import com.CRUD_Project.entities.GenreBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreBookRepository extends JpaRepository<GenreBook,Integer> {
}
