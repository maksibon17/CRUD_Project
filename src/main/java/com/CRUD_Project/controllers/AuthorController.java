package com.CRUD_Project.controllers;

import com.CRUD_Project.dto.AuthorDTO;
import com.CRUD_Project.dto.ReaderDTO;
import com.CRUD_Project.entities.Author;
import com.CRUD_Project.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @GetMapping("/{id}") // поиск автора по id
    public ResponseEntity<AuthorDTO> findAuthorById(@PathVariable(value = "id") Integer id) {
        return authorService.findAuthor(id);
    }

    @GetMapping("/all")// вывод всех авторов
    public ResponseEntity<List<AuthorDTO>> findAllAuthors() {
        return authorService.findAll();
    }

    @PostMapping("/create") // создать автора
    public ResponseEntity<AuthorDTO> createAuthor(@RequestBody AuthorDTO authorDTO) {
        return authorService.create(authorDTO);
    }

    @PutMapping("/{id}/edit") // изменение полей автора
    public ResponseEntity<AuthorDTO> editAuthor(@PathVariable(value = "id") Integer id,
                                                @RequestBody AuthorDTO authorDTO) {
        return authorService.edit(id, authorDTO);
    }

    @DeleteMapping("/{id}/remove") // удаление автора по id
    public ResponseEntity<String> deleteAuthor(@PathVariable(value = "id") Integer id) {
        return authorService.delete(id);
    }
}
