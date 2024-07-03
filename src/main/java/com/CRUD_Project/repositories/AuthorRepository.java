package com.CRUD_Project.repositories;

import com.CRUD_Project.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
