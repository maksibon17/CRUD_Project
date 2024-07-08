package com.CRUD_Project.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record ReaderDTO (
        @Schema(description = "Имя читателя", example = "Иван")
        String name,

        @Schema(description = "Почта читателя", example = "supermaks2007@example.com")
        String email) {
}
