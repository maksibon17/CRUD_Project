package com.CRUD_Project.controllers;

import com.CRUD_Project.entities.Book;
import com.CRUD_Project.services.BookService;
import com.CRUD_Project.services.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/book/{id}") // поиск книги по id
    public String findBookById(@PathVariable(value = "id") Integer id) {
        return bookService.findBook(id);
    }

    @GetMapping("/books") // вывод всех книг
    public List<Book> findAllBooks() {
        return bookService.findAll();
    }

    @PostMapping("/book/create") // создать книгу
    public String createBook(@RequestParam String name, @RequestParam Integer authorId, @RequestParam Integer genreId) {
        return bookService.create(name, authorId, genreId);
    }

    @PostMapping("/book/{id}/edit") // изменения полей книги
    public String editBook(@PathVariable(value = "id") Integer id, @RequestParam String name) {
        return bookService.edit(id, name);
    }

    @PostMapping("/book/{id}/remove") // удаляем книгу по id
    public String deleteBook(@PathVariable(value = "id") Integer id) {
        return bookService.delete(id);
    }
}
