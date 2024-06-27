package com.CRUD_Project.controllers;

import com.CRUD_Project.entities.GenreBook;
import com.CRUD_Project.services.GenreBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GenreBookController {
    @Autowired
    private GenreBookService genreBookService;

    @GetMapping("/genre/{id}") // поиск жанра по id
    public String findGenreById(@PathVariable(value = "id") Integer id) {
        return genreBookService.findGenre(id);
    }

    @GetMapping("/genres") // вывод всех жанров
    public List<GenreBook> findAllGenres() {
        return genreBookService.findAll();
    }

    @PostMapping("/genre/create") // создать жанр
    public String createGenre(@RequestParam String name) {
        return genreBookService.create(name);
    }

    @PostMapping("/genre/{id}/edit") // изменение названия жанра
    public String editGenre(@PathVariable(value = "id") Integer id, @RequestParam String name) {
        return genreBookService.edit(id, name);
    }

    @PostMapping("/genre/{id}/remove") // удаление жанра по id
    public String deleteGenre(@PathVariable(value = "id") Integer id) {
        return genreBookService.delete(id);
    }
}
