package com.CRUD_Project.dto;

import java.time.LocalDate;
import java.util.Date;

public record ExtraditionDTO(
    Integer bookId,
    Integer readerId,
    LocalDate dateIssue,
    LocalDate dateReturn) {
}
