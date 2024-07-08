package com.CRUD_Project.controllers;

import com.CRUD_Project.dto.AuthorDTO;
import com.CRUD_Project.services.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
@Tag(name = "Авторы",
     description = "Операции, связанные с авторами")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    @Operation(summary = "Получить список всех авторов.",
               description = "Возвращает список всех авторов в системе")
    public ResponseEntity<List<AuthorDTO>> getAllAuthors() {
        return authorService.gelAllAuthors();
    }

    @PostMapping("/create")
    @Operation(summary = "Создать автора",
               description = "Создает нового автора в системе на основе предоставленного объекта AuthorDTO")
    public ResponseEntity<AuthorDTO> createAuthor(
            @Parameter(description = "Объект AuthorDTO для создания нового автора", required = true)
            @RequestBody AuthorDTO authorDTO) {
        return authorService.createAuthor(authorDTO);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Найти автора по ID",
               description = " Возвращает информацию об авторе с указанным ID")
    public ResponseEntity<AuthorDTO> getAuthorById(
            @Parameter(description = "ID автора", required = true)
            @PathVariable(value = "id") Integer id) {
        return authorService.getAuthorById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить информацию об авторе",
               description = "Обновляет информацию об авторе с указанным ID на основе предоставленного объекта AuthorDTO")
    public ResponseEntity<AuthorDTO> editAuthor(
            @Parameter(description = "ID автора", required = true)
            @PathVariable(value = "id") Integer id,
            @Parameter(description = "Обновленная информация об авторе", required = true)
            @RequestBody AuthorDTO authorDTO) {
        return authorService.updateAuthor(id, authorDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить автора",
               description = " Удаляет автора с указанным ID из системы.")
    public ResponseEntity<String> deleteAuthor(
            @Parameter(description = "ID автора", required = true)
            @PathVariable(value = "id") Integer id) {
        return authorService.deleteAuthor(id);
    }
}
