package com.CRUD_Project.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record AuthorDTO(
        @Schema(description = "Имя автора", example = "Иван")
        String name,

        @Schema(description = "Фамилия автора", example = "Иванов")
        String surname) {
}
