package com.CRUD_Project.repositories;

import com.CRUD_Project.entities.Book;
import com.CRUD_Project.entities.Extradition;
import com.CRUD_Project.entities.Reader;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface ExtraditionRepository extends JpaRepository<Extradition, Integer> {
    Optional<Extradition> findByBookAndReaderAndDateIssueAndDateReturn(Book book,
                                                          Reader reader,
                                                          LocalDate dateIssue,
                                                          LocalDate dateReturn);
}
