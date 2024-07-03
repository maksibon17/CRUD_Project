package com.CRUD_Project.repositories;

import com.CRUD_Project.entities.GenreBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreBookRepository extends JpaRepository<GenreBook,Integer> {
}
