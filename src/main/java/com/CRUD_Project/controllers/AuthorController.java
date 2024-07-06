package com.CRUD_Project.controllers;

import com.CRUD_Project.dto.AuthorDTO;
import com.CRUD_Project.dto.ReaderDTO;
import com.CRUD_Project.entities.Author;
import com.CRUD_Project.services.AuthorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @GetMapping// вывод всех авторов
    public ResponseEntity<List<AuthorDTO>> gelAllAuthors() {
        return authorService.gelAllAuthors();
    }

    @PostMapping("/create") // создать автора
    public ResponseEntity<AuthorDTO> createAuthor(@RequestBody AuthorDTO authorDTO) {
        return authorService.createAuthor(authorDTO);
    }

    @GetMapping("/{id}") // поиск автора по id
    public ResponseEntity<AuthorDTO> getAuthor(@PathVariable(value = "id") Integer id) {
        return authorService.getAuthorById(id);
    }

    @PutMapping("/{id}") // изменение полей автора
    public ResponseEntity<AuthorDTO> editAuthor(@PathVariable(value = "id") Integer id,
                                                @RequestBody AuthorDTO authorDTO) {
        return authorService.updateAuthor(id, authorDTO);
    }

    @DeleteMapping("/{id}") // удаление автора по id
    public ResponseEntity<String> deleteAuthor(@PathVariable(value = "id") Integer id) {
        return authorService.deleteAuthor(id);
    }
}
