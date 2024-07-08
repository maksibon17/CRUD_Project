package com.CRUD_Project.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record BookDTO(
        @Schema(description = "Название книги", example = "Мы")
        String title,

        @Schema(description = "ID автора книги", example = "1")
        Integer authorId,

        @Schema(description = "ID жанра книги", example = "2")
        Integer genreBookId) {
}
