package com.CRUD_Project.controllers;

import com.CRUD_Project.dto.BookDTO;
import com.CRUD_Project.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/{id}") // поиск книги по id
    public ResponseEntity<BookDTO> findBookById(@PathVariable(value = "id") Integer id) {
        return bookService.findBook(id);
    }

    @GetMapping("/all") // вывод всех книг
    public ResponseEntity<List<BookDTO>> findAllBooks() {
        return bookService.findAll();
    }

    @PostMapping("/create") // создать книгу
    public ResponseEntity<?> createBook(@RequestBody BookDTO bookDTO) {
        return bookService.create(bookDTO);
    }

    @PutMapping("/{id}/edit") // изменения полей книги
    public ResponseEntity<?> editBook(@PathVariable(value = "id") Integer id, @RequestBody BookDTO bookDTO) {
        return bookService.edit(id, bookDTO);
    }

    @DeleteMapping("/{id}/remove") // удаляем книгу по id
    public ResponseEntity<String> deleteBook(@PathVariable(value = "id") Integer id) {
        return bookService.delete(id);
    }
}
