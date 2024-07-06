package com.CRUD_Project.dto;

import com.CRUD_Project.entities.Author;
import com.CRUD_Project.entities.GenreBook;

public record BookDTO(
    String title,
    Integer authorId,
    Integer genreBookId) {
}
