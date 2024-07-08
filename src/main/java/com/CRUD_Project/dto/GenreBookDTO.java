package com.CRUD_Project.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record GenreBookDTO(
        @Schema(description = "Название жанра", example = "Фантастика")
        String name) {
}
