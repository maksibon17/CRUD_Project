package com.CRUD_Project.controllers;

import com.CRUD_Project.dto.GenreBookDTO;
import com.CRUD_Project.dto.ReaderDTO;
import com.CRUD_Project.entities.GenreBook;
import com.CRUD_Project.services.GenreBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genre")
public class GenreBookController {

    @Autowired
    private GenreBookService genreBookService;

    @GetMapping("/{id}") // поиск жанра по id
    public ResponseEntity<GenreBookDTO> findGenreById(@PathVariable(value = "id") Integer id) {
        return genreBookService.findGenre(id);
    }

    @GetMapping("/all") // вывод всех жанров
    public ResponseEntity<List<GenreBookDTO>> findAllGenres() {
        return genreBookService.findAll();
    }

    @PostMapping("/create") // создать жанр
    public ResponseEntity<?> createGenre(@RequestBody GenreBookDTO genreBookDTO) {
        return genreBookService.create(genreBookDTO);
    }

    @PutMapping("/{id}/edit") //изменения полей читателя
    public ResponseEntity<GenreBookDTO> editGenre(@PathVariable Integer id, @RequestBody GenreBookDTO genreBookDTO) {
        return genreBookService.edit(id, genreBookDTO);
    }

    @DeleteMapping("/{id}/remove") // удаляем читателя по id
    public ResponseEntity<String> deleteGenre(@PathVariable(value = "id") Integer id) {
        return genreBookService.delete(id);
    }
}