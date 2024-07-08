package com.CRUD_Project.controllers;

import com.CRUD_Project.dto.GenreBookDTO;
import com.CRUD_Project.services.GenreBookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genres")
@Tag(name = "Жанры",
     description = "Операции, связанные с жанрами книг")
public class GenreBookController {

    private final GenreBookService genreBookService;

    public GenreBookController(GenreBookService genreBookService) {
        this.genreBookService = genreBookService;
    }

    @GetMapping
    @Operation(summary = "Получить список всех жанров",
               description = "Возвращает список всех жанров книг в системе")
    public ResponseEntity<List<GenreBookDTO>> findAllGenres() {
        return genreBookService.findAll();
    }

    @PostMapping("/create")
    @Operation(summary = "Создать жанр",
               description = "Создает новый жанр в системе на основе предоставленного объекта GenreBookDTO")
    public ResponseEntity<?> createGenre(
            @Parameter(description = "Объект GenreBookDTO для создания нового жанра", required = true)
            @RequestBody GenreBookDTO genreBookDTO) {
        return genreBookService.create(genreBookDTO);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Найти жанр по ID",
               description = "Возвращает информацию о жанре с указанным ID")
    public ResponseEntity<GenreBookDTO> findGenreById(
            @Parameter(description = "ID жанра", required = true)
            @PathVariable(value = "id") Integer id) {
        return genreBookService.findGenre(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить информацию о жанре",
               description = "Обновляет информацию о жанре с указанным ID на основе предоставленного объекта GenreBookDTO")
    public ResponseEntity<GenreBookDTO> editGenre(
            @Parameter(description = "ID жанра", required = true)
            @PathVariable Integer id,
            @Parameter(description = "Обновленная информация о жанре", required = true)
            @RequestBody GenreBookDTO genreBookDTO) {
        return genreBookService.edit(id, genreBookDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить жанр",
               description = "Удаляет жанр с указанным ID из системы")
    public ResponseEntity<String> deleteGenre(
            @Parameter(description = "ID жанра", required = true)
            @PathVariable(value = "id") Integer id) {
        return genreBookService.delete(id);
    }
}
