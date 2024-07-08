package com.CRUD_Project.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;

public record ExtraditionDTO(
        @Schema(description = "ID книги", example = "1")
        Integer bookId,

        @Schema(description = "ID читателя", example = "1")
        Integer readerId,

        @Schema(description = "Дата выдачи книги", example = "2023-07-01")
        LocalDate dateIssue,

        @Schema(description = "Дата возврата книги", example = "2023-07-15")
        LocalDate dateReturn) {
}
