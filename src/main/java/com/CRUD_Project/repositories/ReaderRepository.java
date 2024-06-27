package com.CRUD_Project.repository;

import com.CRUD_Project.entities.Reader;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReaderRepository extends JpaRepository<Reader, Integer> {
    Reader findByEmail(String email);
}
