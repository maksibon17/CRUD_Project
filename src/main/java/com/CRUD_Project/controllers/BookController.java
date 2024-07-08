package com.CRUD_Project.controllers;

import com.CRUD_Project.dto.BookDTO;
import com.CRUD_Project.services.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@Tag(name = "Книги",
     description = "Операции, связанные с книгами")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    @Operation(summary = "Получить список всех книг",
               description = "Возвращает список всех книг в системе")
    public ResponseEntity<List<BookDTO>> findAllBooks() {
        return bookService.findAll();
    }

    @PostMapping("/create")
    @Operation(summary = "Создать книгу",
               description = "Создает новую книгу в системе на основе предоставленного объекта BookDTO")
    public ResponseEntity<?> createBook(
            @Parameter(description = "Объект BookDTO для создания новой книги", required = true)
            @RequestBody BookDTO bookDTO) {
        return bookService.create(bookDTO);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Найти книгу по ID",
               description = "Возвращает информацию о книге с указанным ID")
    public ResponseEntity<BookDTO> findBookById(
            @Parameter(description = "ID книги", required = true)
            @PathVariable(value = "id") Integer id) {
        return bookService.findBook(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить информацию о книге",
               description = "Обновляет информацию о книге с указанным ID на основе предоставленного объекта BookDTO")
    public ResponseEntity<?> editBook(
            @Parameter(description = "ID книги", required = true)
            @PathVariable(value = "id") Integer id,
            @Parameter(description = "Обновленная информация о книге", required = true)
            @RequestBody BookDTO bookDTO) {
        return bookService.edit(id, bookDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить книгу",
               description = "Удаляет книгу с указанным ID из системы")
    public ResponseEntity<String> deleteBook(
            @Parameter(description = "ID книги", required = true)
            @PathVariable(value = "id") Integer id) {
        return bookService.delete(id);
    }
}
