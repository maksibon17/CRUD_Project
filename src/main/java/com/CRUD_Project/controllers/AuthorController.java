package com.CRUD_Project.controllers;

import com.CRUD_Project.entities.Author;
import com.CRUD_Project.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @GetMapping("/{id}") // поиск автора по id
    public String findAuthorById(@PathVariable(value = "id") Integer id) {
        return authorService.findAuthor(id);
    }

    @GetMapping // вывод всех авторов
    public List<Author> findAllAuthors() {
        return authorService.findAll();
    }

    @PostMapping("/create") // создать автора
    public String createAuthor(@RequestParam String name, @RequestParam String surname, @RequestParam String dateOfBirthday) {
        return authorService.create(name, surname, dateOfBirthday);
    }

    @PostMapping("/{id}/edit") // изменение полей автора
    public String editAuthor(@PathVariable(value = "id") Integer id, @RequestParam String name, @RequestParam String surname, @RequestParam String dateOfBirthday) {
        return authorService.edit(id, name, surname, dateOfBirthday);
    }

    @PostMapping("/{id}/remove") // удаление автора по id
    public String deleteAuthor(@PathVariable(value = "id") Integer id) {
        return authorService.delete(id);
    }
}
